package com.iwinner.wats.rs.utils;

import org.springframework.beans.BeansException;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.iwinner.wats.rs.dao.SelectionDaoIF;



public class BeanFactory 
{
	private static final XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));	
	
	private BeanFactory()
	{
		// make sure no one can instantiate the class
	}
	
	public static Object getBean(String beanName)
	{
		
		return getBean(beanName, false);
	}
		
	public static Object getBean(String beanName, boolean required)
	{
		if (beanName == null)
		{
	//		throw new ApplicationException("BeanFactory.getBean(beanName) was passed a null beanName parameter");
		}
		
		Object bean = null;
		
		try
		{
			bean = beanFactory.getBean(beanName);
		}
		catch (NoSuchBeanDefinitionException nsbde)
		{
			if (required)
			{
//				ApplicationException ae = new ApplicationException("A required bean " + beanName + " was not found in the beans.xml file");
			//	throw ae;
			}
		}
		catch (BeansException be)
		{
	//		Tracer.error(ExceptionUtil.getStackTrace("BeansException in BeanFactory.getBean", be));			
			//ApplicationException anApplicationException = new ApplicationException("A bean named " + beanName + " has thrown an exception: " + be.getCause());
			//anApplicationException.fillInStackTrace();
		//	throw anApplicationException;
		}
		
		return bean;
	}

	public static SelectionDaoIF getSpotifyDaoImpl() 
	{
		SelectionDaoIF dao = (SelectionDaoIF)getBean("spotifyIntegration", true);
		
		return dao;
	}

}
