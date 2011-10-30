package com.es.zumeh.server.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.es.zumeh.client.model.Password;
import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.server.model.DAOManager;
import com.es.zumeh.server.model.persistence.User;

public class UserDAOTest {
	
	static final String FILE_SEPARATOR = File.separator;
	
	
	DAOManager daoManager;
	
	@Before
	public void setUp() {
		daoManager = new DAOManager();
	}
	
	@After
	public void deleteAllFiles() {
		daoManager.deleteAllUsers();
	}
	
	
	
	@Test
	public void test_add_user() {
		boolean result = addUser();
		Assert.assertEquals(true, result);
	}
	
//	@Test
//	public void test_add_user_with_photo() {
//		boolean result = addUserWithFoto();
//		Assert.assertEquals(true, result);
//	}

	@Test
	public void test_verify_user_by_email_login() {
		addUser();
		
		User user = new User();
		user.setLogin("teste");
		user.setEmail("tiagooleite@gmail.com");
		
		boolean result = daoManager.verifyUser(user);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void test_verify_user_by_attibutes() throws Exception {
		addUser();
		
		User user = daoManager.getUser("teste");
		boolean checkPass = setPassword(user);
		
		Assert.assertEquals("teste", user.getLogin());
		Assert.assertEquals("Pesqueira", user.getLocation());
		Assert.assertEquals("27/12/1987", user.getBirthday());
		Assert.assertEquals("tiagooleite@gmail.com", user.getEmail());
		Assert.assertEquals("M", user.getGender());
		Assert.assertEquals("qualquer area", user.getInterestedArea());
		Assert.assertEquals("PESAO DA SILVA", user.getName());
		Assert.assertEquals(true, checkPass);
		Assert.assertEquals("um cara MUITO DOIDO QUE GOSTA" +
				" DE INSERIR COISAS MUITO GRANDES", user.getWhoAreYou());
		
//		try { //TODO DESCOMENTAR AQUI TVBM
//			FileOutputStream fos = new FileOutputStream("C:" +FILE_SEPARATOR +"images"+ FILE_SEPARATOR+"output.jpg");
//			//fos.write(user.getImage());
//			fos.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private boolean setPassword(User user) throws Exception {
		Password pass = new Password();
		pass.setPassword(user.getPassword());
		boolean checkPass = pass.checkPasswords(user.getPassword());
		return checkPass;
	}
	
	@Test
	public void test_delete_user() {
		//TODO
	}

	private boolean addUser() {
		UserTO user = new UserTO();
		user.setLogin("teste");
		user.setPassword("leite2");
		user.setBirthday("27/12/1987");
		user.setEmail("tiagooleite@gmail.com");
		user.setWhoAreYou("um cara MUITO DOIDO QUE GOSTA" +
				" DE INSERIR COISAS MUITO GRANDES");
		user.setGender("M");
		user.setLocation("Pesqueira");
		user.setInterestedArea("qualquer area");
		user.setName("PESAO DA SILVA");
		
		boolean result = false;
		
		result = daoManager.addUser(user);
		return result;
	}
	
	private boolean addUserWithFoto() {
		UserTO user = new UserTO();
		user.setLogin("teste");
		user.setPassword("leite2");
		user.setBirthday("27/12/1987");
		user.setEmail("tiagooleite@gmail.com");
		user.setWhoAreYou("um cara MUITO DOIDO QUE GOSTA" +
				" DE INSERIR COISAS MUITO GRANDES");
		user.setGender("M");
		user.setLocation("Pesqueira");
		user.setInterestedArea("qualquer area");
		user.setName("PESAO DA SILVA");
		
		File file = new File("C:" + FILE_SEPARATOR + "eu.jpg");
		//File file = new File("images" + FILE_SEPARATOR + "sheldon.jpg");
		
		byte[] bFile = new byte[(int) file.length()];
		
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			// convert file into array of bytes
			fileInputStream.read(bFile);
			user.setImage(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean result = false;
		
		result = daoManager.addUser(user);
		return result;
	}
	

}
