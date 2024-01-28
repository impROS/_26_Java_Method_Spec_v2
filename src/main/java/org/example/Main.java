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

/*
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class RunGeneratedCode {

    public static void main(String[] args) throws Exception {
        // MethodSpec oluşturma
        MethodSpec myMethod = MethodSpec.methodBuilder("myMethod")
                .addModifiers(javax.lang.model.element.Modifier.PUBLIC, javax.lang.model.element.Modifier.STATIC)
                .returns(void.class)
                .addStatement("System.out.println(\"Hello, World!\")")
                .build();

        // TypeSpec (sınıf) oluşturma ve yukarıda oluşturduğumuz metodu ekme
        TypeSpec myClass = TypeSpec.classBuilder("MyClass")
                .addModifiers(javax.lang.model.element.Modifier.PUBLIC, javax.lang.model.element.Modifier.FINAL)
                .addMethod(myMethod)
                .build();

        // Java dosyasını oluşturma
        JavaFile javaFile = JavaFile.builder("com.example", myClass)
                .build();

        // Java dosyasını belirtilen bir dizine yazma
        File outputDirectory = new File("src/main/java");
        javaFile.writeTo(outputDirectory);

        // Derleme işlemi
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, javaFile.toJavaFileObject().getFile().getAbsolutePath());
        if (compilationResult != 0) {
            throw new RuntimeException("Compilation failed.");
        }

        // URLClassLoader ile sınıfın yüklenmesi
        URLClassLoader classLoader = new URLClassLoader(new URL[]{outputDirectory.toURI().toURL()});
        Class<?> myClassType = classLoader.loadClass("com.example.MyClass");

        // Yüklenen sınıfın bir örneğini oluşturma
        Object instance = myClassType.getDeclaredConstructor().newInstance();

        // Oluşturulan sınıfın metodu çağrılabilir
        myClassType.getMethod("myMethod").invoke(instance);
    }
}

 */