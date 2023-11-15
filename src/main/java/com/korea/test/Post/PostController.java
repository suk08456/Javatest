package com.korea.test.Post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/")
    public String main(Model model) {
        //1. DB에서 데이터 꺼내오기
        List<Post> postList = postService.getList();

        if(postList.isEmpty()){
            postService.saveDefault();
        }

        //2. 꺼내온 데이터를 템플릿으로 보내기
        model.addAttribute("postList", postList);
        model.addAttribute("targetPost", postList.get(0));

        return "main";
    }

    @PostMapping("/write")
    public String write() {
        postService.saveDefault();

        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        Post post = postService.getPost(id);
        model.addAttribute("targetPost", post);
        model.addAttribute("postList", postService.getList());

        return "main";
    }

    @PostMapping("/update")
    public String update(Long id, String title, String content) {
        Post post = postService.getPost(id);

        if(title.trim().length() == 0){
            title = "제목 없음";
        }

        post.setTitle(title);
        post.setContent(content);

        postService.save(post);
        return "redirect:/detail/" + id;
    }

    @PostMapping("/delete")
    public String delete(Long id){
        postService.deleteById(id);

        return "redirect:/";
    }
}