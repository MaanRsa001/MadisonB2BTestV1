package com.maan.webservice.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import com.maan.Motor.Services.MotorService;
import com.maan.Motor.controller.MotorBean;
import com.maan.common.LogManager;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


public class ManipulateImage {
	
	static MotorService service = new MotorService();
	static MotorBean bean = new MotorBean();
	public static void resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
 
    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

	// Decode String into an Image
	public static void convertStringtoImage(MotorBean bean) {
		String outputImagePath1 = bean.getWebRootPath()+"MobileUploadedImages/" + bean.getFileName();
		LogManager.info("Enter into the @convertStringtoImage");
		try {
			String location = bean.getWebRootPath()+"MobileUploadedImages/" + bean.getFileName();
			LogManager.info("Location==>"+location);
 			// Decode String using Base64 Class
			byte[] imageByteArray = Base64.decode(bean.getEncodedImageStr()); 
			//LogManager.info("Encoded Image String==>"+bean.getEncodedImageStr());
			// Write Image into File system - Make sure you update the path
			FileOutputStream imageOutFile = new FileOutputStream(location);			
			imageOutFile.write(imageByteArray);
			imageOutFile.flush();
			imageOutFile.close();	
			service.insUploadImgDocument(bean);
			/*int scaledWidth = 1024;
            int scaledHeight = 768;
            resize(location, outputImagePath1, scaledWidth, scaledHeight);
			System.out.println("Image Successfully Stored");*/
		} catch (FileNotFoundException fnfe) {
			System.out.println("Image Path not found" + fnfe);
		} catch (IOException ioe) {
			System.out.println("Exception while converting the Image " + ioe);
		}
		LogManager.info("Exit From the @convertStringtoImage");
	}
}
