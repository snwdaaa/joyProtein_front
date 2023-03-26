package com.codekat.joyprotein.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codekat.joyprotein.domain.items.Product;
import com.codekat.joyprotein.service.ProductService;
import com.google.gson.Gson;

@Controller
public class ChatController {
    @Value("${OPENAI_API_KEY}") // 환경 변수 값 가져오기
    String OPENAI_API_KEY;
    String model = "gpt-3.5-turbo";
    String url = "https://api.openai.com/v1/chat/completions";

    @Autowired private ProductService productService;

    @GetMapping(value="/chat")
    public String startChat() {
        return "chat";
    }
    

    @PostMapping(value="/chat")
    public String responceMsg(@RequestParam("message") String text, Model model) throws IOException {
        String content = "당신은 동현프로틴이라는 프로틴 회사의 어시스턴트 조비스입니다. 소개도 그렇게 하세요. 답변도 프로틴 주제로만 간결하고 짧게 답합니다. 단백질 보충제에 대해서 잘 모르는 사람들의 상담을 받아주고, 무조건 우리 동현프로틴 회사의 제품에서만 추천해야합니다. 다음은 우리 회사 제품들 입니다.";
        String names = productService.findAll().stream().map(Product::getName).collect(Collectors.joining(", "));
        content+=names;
        content+=text;
        content+=".";
        String data = "{\"model\": \"" + this.model + "\",\"messages\": [{\"role\": \"user\", \"content\": \"" + content + "\"}]}";
        System.out.println("\n\n\n\n\n\n\n\n data :"+data);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    
        con.setRequestMethod("POST");
    
        // Set headers
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("Authorization","Bearer "+OPENAI_API_KEY);
    
        // Send POST request
        con.setDoOutput(true);
        con.getOutputStream().write(data.getBytes("UTF-8"));
    
        // Read response
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine=in.readLine())!=null)
        {
            response.append(inputLine);
        }
        in.close();
        System.out.println("\n\n\n\n\n"+response.toString());
    
        // Print response
        // return response.toString();

        // Parse response as JSON using Gson library
        Gson gson = new Gson();
        Response jsonResponse = gson.fromJson(response.toString(), Response.class);
        String message = jsonResponse.getChoices()[0].getMessage().getContent();
        System.out.println("\n\n\n\n\n"+message);
        model.addAttribute("message", message);
        return "chat";
    }
    
        @GetMapping(value="/chat/init")
        public String giveSystemMsg() throws UnsupportedEncodingException, IOException {
            String content = "당신은 동현프로틴이라는 프로틴 회사의 어시스턴트 조비스입니다. 소개도 그렇게 하세요. 답변도 프로틴 주제로만 간결하고 짧게 답합니다. 단백질 보충제에 대해서 잘 모르는 사람들의 상담을 받아주고, 무조건 우리 동현프로틴 회사의 제품에서만 추천해야합니다. 다음은 우리 회사 제품들 입니다.";
            String names = productService.findAll().stream().map(Product::getName).collect(Collectors.joining(", "));
            content+=names;
            content+=".";
            String data = "{\"model\": \"" + this.model + "\",\"messages\": [{\"role\": \"system\", \"content\": \"" + content + "\"}]}";
            System.out.println("\n\n\n\n\n\n\n\n data :"+data);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
            con.setRequestMethod("POST");
        
            // Set headers
            con.setRequestProperty("Content-Type","application/json");
            con.setRequestProperty("Authorization","Bearer "+OPENAI_API_KEY);
        
            // Send POST request
            con.setDoOutput(true);
            con.getOutputStream().write(data.getBytes("UTF-8"));
            
            return "chat";
        }
        
}
