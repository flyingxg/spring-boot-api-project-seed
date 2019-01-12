package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Message;
import com.company.project.service.QuestionUtils;
import com.company.project.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/01/12.
*/
@Controller
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;

    @RequestMapping("/")
    public String index(){
        return "problem";
    }

    @PostMapping("/getAns")
    @ResponseBody
    public String ans(Message message) {
        String answer = QuestionUtils.getIntroduction(message.getProblem());
        message.setAnswer(answer);
        //转义字符串的斜杠
        answer = answer.replaceAll("\\\\","");
        messageService.save(message);
        return answer;
    }


    @PostMapping("/list")
    @ResponseBody
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Message> list = messageService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
