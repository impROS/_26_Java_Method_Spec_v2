package org.example;

import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.MethodSpec;
import org.springframework.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // MethodSpec oluşturma
        MethodSpec myMethod = MethodSpec.methodBuilder("myMethod")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addStatement("System.out.println(\"Hello, World!\")")
                .build();

        // TypeSpec (sınıf) oluşturma ve yukarıda oluşturduğumuz metodu ekme
        TypeSpec myClass = TypeSpec.classBuilder("MyClass")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(myMethod)
                .build();


        // Java dosyasını oluşturma
        JavaFile javaFile = JavaFile.builder("com.example", myClass)
                .build();

        // Oluşturulan Java dosyasını konsola yazdırma
        try {
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}