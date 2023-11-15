package com.korea.test.NoteBook;


import com.korea.test.Post.Post;
import com.korea.test.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notebook")
public class NotebookController {

    private final NotebookService notebookService;
    private final PostService postService;

}
