package com.example.MediaTech;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MediaTechApplicationTests {

	@Autowired
	private MediaTechApplication mediaTechApplication;

	@Autowired
	private ModelMapper modelMapper;

	@Test
	void contextLoads() {
	//	assert mediaTechApplication.modelMapper() != null;
		assertThat(mediaTechApplication).isNotNull();
		assertThat(modelMapper).isNotNull();

	}

}
