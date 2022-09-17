package com.example.lab04;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "index2")
public class CashierView extends VerticalLayout {
    public CashierView() {
        TextField changeAmount = new TextField("เงินทอน");
        Button calculateBtn = new Button("คำนวณเงินทอน");

        TextField b1000, b500, b100, b20, b10, b5, b1;
        b1000 = new TextField();
        b1000.setValue("$1000: ");
        b1000.setReadOnly(true);

        b500 = new TextField();
        b500.setValue("$500: ");
        b500.setReadOnly(true);

        b100 = new TextField();
        b100.setValue("$100: ");
        b100.setReadOnly(true);

        b20 = new TextField();
        b20.setValue("$20: ");
        b20.setReadOnly(true);

        b10 = new TextField();
        b10.setValue("$10: ");
        b10.setReadOnly(true);

        b5 = new TextField();
        b5.setValue("$5: ");
        b5.setReadOnly(true);

        b1 = new TextField();
        b1.setValue("$1: ");
        b1.setReadOnly(true);

        add(changeAmount, calculateBtn, b1000, b500, b100, b20, b10, b5, b1);

        calculateBtn.addClickListener(event -> {
           int amount = Integer.parseInt(changeAmount.getValue());
           Change out = WebClient.create()
                   .get()
                   .uri("http://127.0.0.1:8080/getChange/" + amount)
                   .retrieve()
                   .bodyToMono(Change.class)
                   .block();
           b1000.setValue("$1000: " + out.getB1000());
           b500.setValue("$500: " + out.getB500());
           b100.setValue("$100: " + out.getB100());
           b20.setValue("$20: " + out.getB20());
           b10.setValue("$10: " + out.getB10());
           b5.setValue("$5: " + out.getB5());
           b1.setValue("$1: " + out.getB1());
        });
    }
}
