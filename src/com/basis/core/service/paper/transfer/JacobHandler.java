package com.basis.core.service.paper.transfer;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class JacobHandler implements Handler{

	
	
	public void word2Html(String source,String target){
		try{
			ActiveXComponent app = new ActiveXComponent("Word.Application");  
		    app.setProperty("Visible", new Variant(false));  
		    Dispatch doc1 = app.getProperty("Documents").toDispatch();  
		    //打开doc  
		    Dispatch doc2 = Dispatch.invoke(  
		        doc1,   
		        "Open",   
		        Dispatch.Method,  
		        new Object[]{source, new Variant(false), new Variant(true)},  
		        new int[1]  
		    ).toDispatch();  
		    //另存为html  
		    Dispatch.invoke(  
		        doc2,  
		        "SaveAs",   
		        Dispatch.Method,   
		        new Object[]{  
		            target,   
		            new Variant(8)//7为txt格式， 8保存为html格式  
		        },   
		        new int[1]  
		    );  
		    Variant f = new Variant(false);  
		    Dispatch.call(doc2, "Close", f);  
		}catch(Throwable e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
}
