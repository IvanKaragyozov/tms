package pu.master.tmsapi.configurations;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class BeanConfiguration
{

    @Bean
    public ModelMapper modelMapper()
    {
        final ModelMapper modelMapper = new ModelMapper();

        // Configuration to enable mapping through PRIVATE fields
        modelMapper.getConfiguration()
                   .setFieldMatchingEnabled(true)
                   .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return modelMapper;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
