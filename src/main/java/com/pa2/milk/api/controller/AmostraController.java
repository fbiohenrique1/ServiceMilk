package com.pa2.milk.api.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.pa2.milk.api.model.Amostra;
import com.pa2.milk.api.repository.AmostraRepository;
import com.pa2.milk.api.repository.AnaliseRepository;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.vcard.VCard;
import net.glxn.qrgen.javase.QRCode;

@RestController
@RequestMapping(value = "/amostra")
@CrossOrigin(origins = "*")
public class AmostraController {
//O crud de amostra envolve a busca pelo id da solicitação e o id de analise

	@Autowired
	private AmostraRepository amostraRepositorio;

	@Autowired
	private AnaliseRepository analiseRepositorio;

	@GetMapping(value = "{id}/QrCode")
	public void getQRCodeImage(@PathVariable("idAnalise") Integer idAnalise) throws WriterException, IOException {

		List<Amostra> a = this.amostraRepositorio.findByAnalise(idAnalise);

		for (int i = 0; i < a.size(); i++) {

			 VCard vCard = new VCard();
			 vCard.setName("Amostra "+String.valueOf(a.get(i).getId()));
			 vCard.setCompany(String.valueOf(a.get(i).getDataColeta()));
		     vCard.setTitle(String.valueOf(a.get(i).getNumeroAmostra()) );
		     vCard.setEmail(String.valueOf(a.get(i).getObservacao()));
		     vCard.setWebsite(String.valueOf(a.get(i).getQrCode()));
		        
			ByteArrayOutputStream bout = QRCode.from(vCard).withSize(250, 250).to(ImageType.PNG).stream();

			try {
				File file = new File("C:\\Users\\phnf2\\Pictures\\teste\\qr_code("+i+").png");
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

}
