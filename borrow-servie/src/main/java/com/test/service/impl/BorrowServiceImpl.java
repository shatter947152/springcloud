package com.test.service.impl;

import com.test.entity.Book;
import com.test.entity.Borrow;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
import com.test.mapper.BorrowMapper;
import com.test.service.BorrowService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {
@Resource
    BorrowMapper borrowMapper;
    @Override
    public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
        List<Borrow> borrows=borrowMapper.getBorrowByUid(uid);
        RestTemplate restTemplate = new RestTemplate();
        User forObject = restTemplate.getForObject("http://localhost:8101/user/" + uid, User.class);
        List<Book> bookList=borrows
                .stream()
                .map(b->restTemplate.getForObject("http://localhost:8201/book/"+b.getBid(),Book.class))
                .collect(Collectors.toList());
        return new UserBorrowDetail(forObject,bookList);

    }
}
