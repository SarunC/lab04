package com.example.lab04;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "index1")
public class MathView extends VerticalLayout {

    public MathView() {
        TextField num1 = new TextField();
        num1.setLabel("Number 1");
        num1.setWidth("500px");

        TextField num2 = new TextField();
        num2.setLabel("Number 2");
        num2.setWidth("500px");

        Button plus = new Button("+");
        Button minus = new Button("-");
        Button multi = new Button("x");
        Button divide = new Button("/");
        Button mod = new Button("mod");
        Button max = new Button("max");
        Label lb = new Label("Operator");
        HorizontalLayout h1 = new HorizontalLayout();

        TextField ans = new TextField();
        ans.setLabel("Answer");
        ans.setWidth("500px");

        h1.add(plus, minus, multi, divide, mod, max);
        add(num1, num2, lb, h1, ans);

        plus.addClickListener(event -> {
            double n1 = Double.parseDouble(num1.getValue());
            double n2 = Double.parseDouble(num2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/plus/"+n1+"/"+n2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });

        minus.addClickListener(event -> {
            double n1 = Double.parseDouble(num1.getValue());
            double n2 = Double.parseDouble(num2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/minus/"+n1+"/"+n2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });

        multi.addClickListener(event -> {
            double n1 = Double.parseDouble(num1.getValue());
            double n2 = Double.parseDouble(num2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/multi/"+n1+"/"+n2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });

        divide.addClickListener(event -> {
            double n1 = Double.parseDouble(num1.getValue());
            double n2 = Double.parseDouble(num2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/divide/"+n1+"/"+n2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });

        mod.addClickListener(event -> {
            double n1 = Double.parseDouble(num1.getValue());
            double n2 = Double.parseDouble(num2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/mod/"+n1+"/"+n2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });

        max.addClickListener(event -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("n1", num1.getValue());
            formData.add("n2", num2.getValue());
            String out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8080/max/")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
    }
}
