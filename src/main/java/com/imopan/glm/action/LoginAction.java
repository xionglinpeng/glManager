package com.imopan.glm.action;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginAction {
	
	public static void main(String[] args) throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		
		System.out.println(publicKey.getFormat());
		System.out.println("公钥：\n"+Base64.encodeBase64String(publicKey.getEncoded()));
		System.out.println("私钥：\n"+Base64.encodeBase64String(privateKey.getEncoded()));
		
		String a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/RtZxRMnC1jqOHOplDYQIdOL9O2CxAOCJLPkT/r/lnOoRAXmP2hMXW7w7gRzdfI8tiDFW1CYeZWzQJ3unyIeoPWhj/PaxDGvTbXy08eFRhIM13yHp3okJ/A9aklQNOdzjXIv+i+NblQtEfTOyIwfIu9RIQRzn0hZkO4dAYOUL5QIDAQAB";
		String b = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL9G1nFEycLWOo4c6mUNhAh04v07YLEA4Iks+RP+v+Wc6hEBeY/aExdbvDuBHN18jy2IMVbUJh5lbNAne6fIh6g9aGP89rEMa9NtfLTx4VGEgzXfIeneiQn8D1qSVA053ONci/6L41uVC0R9M7IjB8i71EhBHOfSFmQ7h0Bg5QvlAgMBAAECgYBYvv4p6ZePDaR3YVUjc6Tp44mZop4XIegL3VDnhQ/18drr7vo+0xh+gvKPzTTeUrd/kawKtD3HZpmY7mjCDRlbF9JvGmsoR1cJ0RLsBoC4h8nu0eZtXpy+CBq12/eKjssWQsfbyBoHtMROoklTNNaS0XKGnKsrqcE6wu2pPTqGNQJBAPpc79ocRgkdKmtZQZuPuycxjcpvH8S3MILwHf252Ww8OtWjSv5SIwLgTuBa6c4a86q8yTrkmvB0JqggN0neVzMCQQDDlVWIriCq/xtOKCfumOwXkjWOmubz7KI6ays/jJFAqTHgpbbUD+WGyyEzrPLQLsyHlFnk9TSijNwseapkhrCHAkEAtzkwp3StpJndo3ZM12XW15YiaJXur+5I6GqF6FwMzHM/s32vsnO1/7LcxsCB/z4mOTu0IQ+QfIzkhYIeNEctlQJARFKwlJSvmMyRT1Y7zN/i6cELsItwTpnFej7h1stWGo6AfAenvKDo/5OKP7EVCaCvllU+qfr2rioi5+6AlalvlQJBAPhdfTI2HLQK9FMpOwauL3NvNCMGMRvLMvmx1/p5MbtoJn0ANnYHdu6aHMely2NjurTI0/B8DP/pi3f7xoyjcm4=";
		
		System.out.println(a.equals(Base64.encodeBase64String(publicKey.getEncoded())));
		System.out.println(b.equals(Base64.encodeBase64String(privateKey.getEncoded())));
		
		
		
//		X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
//		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//		PublicKey key = keyFactory.generatePublic(encodedKeySpec);
//		System.out.println(key.getFormat());
//		System.out.println(keyFactory.getAlgorithm());
//		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//		cipher.init(cipher.ENCRYPT_MODE, key);
//		byte[] b = cipher.doFinal("你好啊".getBytes());
//		System.out.println(new String(b));
//		System.out.println(Base64.encodeBase64String(b));
		
//		 PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
//        KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");
//        PrivateKey privateK = keyFactory2.generatePrivate(pkcs8KeySpec);
//        Cipher cipher1 = Cipher.getInstance(keyFactory2.getAlgorithm());
//        cipher1.init(Cipher.DECRYPT_MODE, privateK);
//        byte[] b1 = cipher1.doFinal("FBi4lzKmQpcftjvtQronSwDC0BRZeefKFz1fnQKPMjbPgzMrO14XW30RUOYwL/Z9UwDr/Axb5P4Fn2AGDXC0bYGMbzC7MRoF4wf767/meKz0D7lLJSM9h8NsKlda+hQZ8eUzF08JbrVLxB0fKMM6wElOOVJZQzM0FPDmF9Rivzg=".getBytes());
//		System.out.println(Base64.encodeBase64String(b1));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * <p>登录。</p>
	 */
	public void login(){
		
	}
	
	/**
	 * <p>创建新账户。</p>
	 */
	public void register(){
		
	}
	
	/**
	 * <p>修改密码。</p>
	 */
	public void updatePassWord(){
		
	}
	
	/**
	 * <p>忘记密码。</p>
	 */
	public void forgetPassword(){
		
	}
}
