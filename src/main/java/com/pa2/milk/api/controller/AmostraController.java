package com.pa2.milk.api.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

@RestController
@RequestMapping(value = "/amostra")
@CrossOrigin(origins = "*")
public class AmostraController {
//O crud de amostra envolve a busca pelo id da solicitação e o id de analise

	@GetMapping(value = "/text")
	public void getQRCodeImage(@RequestParam("text") String text) throws WriterException, IOException {

		ByteArrayOutputStream bout = QRCode.from(text).withSize(250, 250).to(ImageType.PNG).stream();

		try {
			File file = new File("C:\\Users\\phnf2\\Pictures\\teste\\qr_code.png");
			OutputStream out = new FileOutputStream(file);
			bout.writeTo(out);
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
