
package org.idempiere.tms.form;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.activation.MimetypesFileTypeMap;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;

import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MLocation;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Iframe;


public class MapViewForm  implements IFormController,EventListener {

 	private CustomForm form = new CustomForm();
	private Iframe iframe = new Iframe();
	 
	private Button bRefresh = new Button();
	private Button bCreate = new Button();
	private Button bgmapURL = new Button();
	private Label lversion = new Label(" [v1.03]");

 	private int height_map =0;
 	
	private Label labelFO = new Label("Freight Order ");

	private SimpleDateFormat date_time = new SimpleDateFormat("HHmmss");
	private String Locale = Env.getContext(Env.getCtx(), "#Locale");
	
	private Listbox fieldFO = ListboxFactory.newDropdownListbox();
	
 	private String LocalHttpAddr = Env.getContext(Env.getCtx(), "#LocalHttpAddr");
 	
 	File file_tmp = null;
 	
	public MapViewForm()
	{
		try
		{
			dynInit();
			zkInit();
			//dynInit();
			Borderlayout contentLayout = new Borderlayout();
			ZKUpdateUtil.setWidth(contentLayout, "100%");
			ZKUpdateUtil.setHeight(contentLayout, "100%");

 			form.appendChild(labelFO);		
 			fieldFO.addActionListener(this);
 			form.appendChild(fieldFO);
 			
			bRefresh.setLabel(Msg.translate(Env.getCtx(), "Refresh FO"));
			bRefresh.addActionListener(this);
			form.appendChild(bRefresh);
 			
			bCreate.setLabel(Msg.translate(Env.getCtx(), "Show Route"));
			bCreate.addActionListener(this);
			form.appendChild(bCreate);
			
			bgmapURL.setLabel(Msg.translate(Env.getCtx(), "Show Route[gmap]"));
			bgmapURL.addActionListener(this);
			form.appendChild(bgmapURL);
 								
			form.appendChild(lversion);
			
			form.appendChild(iframe); 
		}
		catch(Exception e)
		{
			//log.log(Level.SEVERE, "", e);
		}
	}
	

	private void zkInit() throws Exception
	{
		int height = Double.valueOf(SessionManager.getAppDesktop().getClientInfo().desktopHeight * 0.8).intValue();
 
		height = height - 5;
		height_map=height;
		iframe.setHeight(height + "px");
		iframe.setWidth("100%");
		iframe.setAutohide(true);
		form.setWidth("100%");
		form.setHeight("100%");
		form.appendChild(iframe);

	}
	
	/**
	 * 	Dynamic Init.
	 */
	private void dynInit()
	{
		//route
		ArrayList<KeyNamePair> FOData = getFOData();
		for(KeyNamePair pp : FOData)
			fieldFO.appendItem(pp.getName(), pp);
		fieldFO.setSelectedIndex(1);		
	}	//	dynInit
	
	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose
	
	public ADForm getForm() {
		return form;
	}
	
	/**************************************************************************
	 *  Action Listener
	 *  @param e event
	 * @throws IOException 
	 */
	public void onEvent(Event e) throws IOException
	{
		if (e.getTarget().equals(bCreate)) 
		{
			iframe.setSrc(null);
			if (fieldFO.getSelectedIndex()!=0)
				{
	 			KeyNamePair key_fo = (KeyNamePair) fieldFO.getSelectedItem().getValue();
 				createhtmlfile(key_fo.getKey());
				
				MimetypesFileTypeMap mimeMap = new MimetypesFileTypeMap();
	            AMedia media = new AMedia(file_tmp, mimeMap.getContentType(file_tmp), "UTF-8");
			
				iframe.setContent(media);
				iframe.invalidate();	
				}		
 		}
		
		if (e.getTarget().equals(bRefresh)) 
		{			
			fieldFO.removeAllItems();
			dynInit();
		}
		
		
		if (e.getTarget().equals(bgmapURL)) 
		{
			    KeyNamePair key_fo = (KeyNamePair) fieldFO.getSelectedItem().getValue();
				String urlString = createGMapURL(key_fo.getKey());
				String message = null;
				try {
					Executions.getCurrent().sendRedirect(urlString, "_blank");
				}
				catch (Exception e1) {
					message = e1.getMessage();
					FDialog.warn(0, "URLnotValid", message.toString());
				}
		}
	}   //  onEvent
	
	
	private void createhtmlfile(int tboxFO_ID) throws IOException {
		String points_str="";
		String sql = "";
		
		//tmpfile
		file_tmp = File.createTempFile("ghmapview", ".html");
				
		FileOutputStream fis_tmp= new FileOutputStream(file_tmp);
		
		OutputStreamWriter osw = new OutputStreamWriter(fis_tmp, "UTF8");
		//tmpfile
		
		Writer out = new BufferedWriter(osw);
//----
		
		out.write("	<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html>");
		out.write("	<html> ");
		out.write("	<body> ");
		out.write("	<iframe ");
		out.write("	width='90%' ");
		out.write("	height='"+(height_map+50) + "px"+"' ");
		out.write("	frameborder='0' style='border:0' ");
		
		// create URL string
		points_str+="src='https://graphhopper.com/maps/?";
   			sql =   "(SELECT DISTINCT ON (fos.C_LocFrom_ID) fos.C_LocFrom_ID " + 
					" FROM DD_Freight_Stop fos  " + 
					" LEFT JOIN C_Location l ON l.C_Location_ID=fos.C_LocFrom_ID   " + 
					" WHERE fos.C_BPartner_ID IS NOT NULL AND fos.DD_Freight_ID="+tboxFO_ID+" ORDER BY fos.C_LocFrom_ID) " + 
					" UNION ALL   " + 
					" (SELECT DISTINCT ON (fos.C_LocTo_ID) fos.C_LocTo_ID " + 
					" FROM DD_Freight_Stop fos   " + 
					" LEFT JOIN C_Location l ON l.C_Location_ID=fos.C_LocTo_ID   " + 
					" WHERE fos.C_LocTo_ID IS  NOT NULL AND fos.DD_Freight_ID="+tboxFO_ID +" ORDER BY fos.C_LocTo_ID);";		
		 

		PreparedStatement pstmt = null;
   			ResultSet rs = null;
   			try
	   			{
	   			pstmt = DB.prepareStatement(sql, null);
	   			rs = pstmt.executeQuery();
	   			while (rs.next())
		   			{ 
		   				if (rs.getInt(1)>0)
		   				{
		   					//points_str+="&point="+rs.getString(1);
		   					MLocation m_loc = new MLocation(Env.getCtx(),rs.getInt(1),null);
		   					points_str+="&point="+m_loc.getMapsLocation();
			   			}
		   			}
	   			}
   			catch (SQLException e)
	   			{
	   			//log.log(Level.SEVERE, sql, e);
	   			}
   			finally
	   			{
		   			DB.close(rs, pstmt);
		   			rs = null; pstmt = null;
	   			}   			
    
		points_str+= "&locale="+Locale;
		points_str+= "&vehicle=small_truck&weighting=fastest&elevation=true&use_miles=false&layer=Omniscale'";
		out.write(points_str);

		out.write("	</iframe> ");
		out.write("	</body> ");
		out.write("	</html> ");			

   		out.close(); osw.close(); fis_tmp.close(); out=null;osw=null;fis_tmp=null;
	}//end createhtmlfile	
	
	
	public ArrayList<KeyNamePair> getFOData()
	{
		ArrayList<KeyNamePair> data = new ArrayList<KeyNamePair>();
		
		KeyNamePair pp = new KeyNamePair(0, "");
		data.add(pp);
		
		String sql ="SELECT DD_Freight_ID, Description FROM DD_Freight WHERE isActive='Y' AND AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx())+" ORDER BY 2";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				data.add(pp);
			}
		}
		catch (SQLException e)
		{
//			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return data;
	}	
	
	 private String createGMapURL(int tboxFO_ID) throws IOException {
	
     // create URL string
		 String url_str="https://www.google.com/maps/dir/";
		 
		 String sql =   "(SELECT DISTINCT ON (fos.C_LocFrom_ID) fos.C_LocFrom_ID " + 
				" FROM DD_Freight_Stop fos  " + 
				" LEFT JOIN C_Location l ON l.C_Location_ID=fos.C_LocFrom_ID   " + 
				" WHERE fos.C_BPartner_ID IS NOT NULL AND fos.DD_Freight_ID="+tboxFO_ID+" ORDER BY fos.C_LocFrom_ID) " + 
				" UNION ALL " + 
				" (SELECT DISTINCT ON (fos.C_LocTo_ID) fos.C_LocTo_ID " + 
				" FROM DD_Freight_Stop fos   " + 
				" LEFT JOIN C_Location l ON l.C_Location_ID=fos.C_LocTo_ID   " + 
				" WHERE fos.C_LocTo_ID IS  NOT NULL AND fos.DD_Freight_ID="+tboxFO_ID +" ORDER BY fos.C_LocTo_ID);";		
 	        PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
   			{
   			pstmt = DB.prepareStatement(sql, null);
   			rs = pstmt.executeQuery();
   			while (rs.next())
	   			{ 
	   				if (rs.getInt(1)>0)
	   				{
	   					MLocation m_loc = new MLocation(Env.getCtx(),rs.getInt(1),null);
	   					url_str+=m_loc.getMapsLocation()+"/" ;
		   			}
	   			}
   			}
			catch (SQLException e)
   			{
   			//log.log(Level.SEVERE, sql, e);
   			}
			finally
   			{
	   			DB.close(rs, pstmt);
	   			rs = null; pstmt = null;
   			}
			return url_str;
	 }
	
}
