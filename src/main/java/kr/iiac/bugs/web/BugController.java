package kr.iiac.bugs.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.iiac.bugs.repository.BugRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BugController {

	@Autowired
	private BugRepository bugRepository;
	
	@RequestMapping(value="/")
	public String index(HttpServletRequest req, Model model,@RequestParam(required=false,value="writer")String writer){
		if(writer != null){
			HttpSession session = req.getSession();
			session.setAttribute("writer", writer);
		}
		model.addAttribute("menus", bugRepository.findAll());
		return "index";
	}
	
	@RequestMapping("/imgUpload")
	public String imgUpload(HttpServletRequest req, ImgVO imgVO){
		String result="";
		try{
			if(imgVO.getFiledata() != null && imgVO.getFiledata().getOriginalFilename() != null){
				
				String path = req.getSession().getServletContext().getRealPath("/") + "resources\\img\\";
				File file = new File(path);
				System.out.print(path);
				if(!file.exists()){
					file.mkdirs();
				}
				String realName = UUID.randomUUID().toString() + imgVO.getFiledata().getOriginalFilename();
				imgVO.getFiledata().transferTo(new File(path + realName));
				result += "&bNewLine=true&sFileName="+imgVO.getFiledata().getOriginalFilename()+"&sFileURL=/resources/img/"+realName;
			}else{
				result += "&errstr=error";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:" + imgVO.getCallback() +"?callback_func=" + imgVO.getCallback_func()+result;
	}

	@RequestMapping("/multiImgUpload")
	public void multiImgUpload(HttpServletRequest req, HttpServletResponse res){
		try{
			String sFileInfo = "";
			String fileName = req.getHeader("file-name");
			String prifix = fileName.substring(fileName.lastIndexOf(".")+1);
			prifix = prifix.toLowerCase();
			String path = req.getSession().getServletContext().getRealPath("/") + "resources\\img\\";
			File file = new File(path);
			System.out.print(path);
			if(!file.exists()){
				file.mkdirs();
			}
			
			String realName = UUID.randomUUID().toString() + "." +prifix;
			
			InputStream is = req.getInputStream();
			OutputStream os = new FileOutputStream(new File(path + realName));
			int read = 0;
			byte b[] = new byte[1024];
			while( (read = is.read(b)) != -1){
				os.write(b,0,read);
			}
			
			if(is != null){
				is.close();
			}
			os.flush();
			os.close();
			
			sFileInfo += "&bNewLine=true";
			sFileInfo += "&sFileName="+fileName;
			sFileInfo += "&sFileURL="+"/resources/img/"+realName;
			PrintWriter print = res.getWriter();
			print.print(sFileInfo);
			print.flush();
			print.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
