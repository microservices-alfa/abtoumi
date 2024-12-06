package com.example.MediaTech;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MediaTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaTechApplication.class, args);
	}


	public ModelMapper modelMapper(){  // convertir des objets de modèle (entités) en objets de vue (DTOs) et vice versa.

		return new ModelMapper();
	}

}
