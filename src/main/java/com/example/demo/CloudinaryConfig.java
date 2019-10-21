package com.example.demo;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryConfig {
    private Cloudinary cloudinary;

    @Autowired
    public CloudinaryConfig(@Value("188162421333356") String key,
                            @Value("-9F8ilH7GO5sa_FoSWr5S_dpOdE") String secret,
                            @Value("ascension-enterprises") String cloud){
        cloudinary = Singleton.getCloudinary();
        cloudinary.config.cloudName=cloud;
        cloudinary.config.apiSecret=secret;
        cloudinary.config.apiKey=key;
    }

    public Map upload(Object file, Map options){
        try{
            return cloudinary.uploader().upload(file, options);
        } catch(IOException e){
            e.printStackTrace();;
            return null;
        }
    }

    public String createUrl(String name, int width, int height, String action) {
        return cloudinary.url()
                .transformation(new Transformation()
//                .width(width).height(height)
                .width(100)
                .border("2x_solid_black").crop("fill"))
                .imageTag(name);
        //.aspectratio{"100")
        //https://cloudinary.com/documentation/java_image_manipulation
    }
}
