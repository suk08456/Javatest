package com.korea.test.Post;


import com.korea.test.NoteBook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final NotebookService notebookService;
    private final PostRepository postRepository;

    public List<Post> getList() {
        return postRepository.findAll();
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public Post getPost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if(postOptional.isPresent()){
            return postOptional.get();
        }else{
            throw new IllegalArgumentException("없는 페이지 입니다.");
        }
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public void saveDefault(){
        Post post = new Post();
        post.setTitle("new title..");
        post.setContent("");
        post.setCreateDate(LocalDateTime.now());

        postRepository.save(post);
    }
}
