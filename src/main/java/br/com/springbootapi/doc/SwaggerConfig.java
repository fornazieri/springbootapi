package br.com.springbootapi.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

//No Spring Boot o Swagger é ativado através da anotação @EnableSwagger2.
// O Docket que estamos definindo no nosso bean nos permite configurar aspectos dos endpoints expostos por ele.
//Nos métodos apis() e paths() definimos que todas as apis e caminhos estarão disponíveis.
//Com isso através de reflection a biblioteca já consegue obter os endpoints definidos na aplicação.
//Ao executá-la, o Swagger UI estará disponível em /swagger-ui.html:
//https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("br.com.springbootapi.controller"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/api/*"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
                .globalResponseMessage(RequestMethod.PUT, responseMessageForPUT())
                .globalResponseMessage(RequestMethod.DELETE, responseMessageForDELETE())
                //.securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
                //.securityContexts(Arrays.asList(securityContext()))
                .apiInfo(apiInfo());
    }

//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.ant("/pessoa/**"))
//                .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("ADMIN", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(
//                new SecurityReference("Token Access", authorizationScopes));
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simple Spring Boot REST API")
                .description("\"Um exemplo de aplicação Spring Boot REST API Com conexão ao FIREBIRD\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Vitor Fornazieri Rodrigues", "", "viitor.f@hotmail.com"))
                .build();
    }

    private List<ResponseMessage> responseMessageForGET()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("500 message")
                    .responseModel(new ModelRef("Error"))
                    .build());
//            add(new ResponseMessageBuilder()
//                    .code(403)
//                    .message("Forbidden!")
//                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found")
                    .build());
        }};
    }

    private List<ResponseMessage> responseMessageForPUT()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("500 message")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found")
                    .build());
        }};
    }

    private List<ResponseMessage> responseMessageForDELETE()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("500 message")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found")
                    .build());
        }};
    }
}