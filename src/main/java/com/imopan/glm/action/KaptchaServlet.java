package com.imopan.glm.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

public class KaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Properties props = new Properties();
	private Producer kaptchaProducer = null;
	private String sessionKeyValue = null;
	
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);

		ImageIO.setUseCache(false);

		Enumeration<?> initParams = conf.getInitParameterNames();
		while (initParams.hasMoreElements()) {
			String key = (String) initParams.nextElement();
			String value = conf.getInitParameter(key);
			this.props.put(key, value);
		}
		Config config = new Config(this.props);
		this.kaptchaProducer = config.getProducerImpl();
		this.sessionKeyValue = config.getSessionKey();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setDateHeader("Expires", 0L);

		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		resp.addHeader("Cache-Control", "post-check=0, pre-check=0");

		resp.setHeader("Pragma", "no-cache");

		resp.setContentType("image/jpeg");

		String capText = this.kaptchaProducer.createText();
		
		Integer a = Integer.valueOf(capText.substring(0, 1));
		Integer b = Integer.valueOf(capText.substring(1, 2));
		
		req.getSession().setAttribute(this.sessionKeyValue, a+b);
		
		BufferedImage bi = this.kaptchaProducer.createImage(a+"+"+b+"=?");

		ServletOutputStream out = resp.getOutputStream();

		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}
	
}
