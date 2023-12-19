package com.example.ex2.Repository;

import com.example.ex2.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests { //check actual Class(Interface type)

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies(){ //insert data(100) on DB by threads
        IntStream.rangeClosed(1,100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..."+i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect(){
        //mno which exist DB
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("====================================");

        if (result.isPresent()){
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Transactional  //Annotation for Transaction processing
    @Test
    public void testSelect2(){
        //mno which exist DB
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("====================================");

        System.out.println(memo);
    }

    @Test
    public void testUpdate(){ //update 100th row
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void testDelete(){ //delete 100th row
        Long mno = 100L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault(){

        // 10 of 1 page
        Pageable pageable = PageRequest.of(0,10);
        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);

        System.out.println("-------------------------------");

        System.out.println("Total Pages: " + result.getTotalPages()); // total pages
        System.out.println("Total Count: " + result.getTotalElements()); // total counts

        System.out.println("Page Number: " + result.getNumber()); // current page number(starts 0)
        System.out.println("Page Size: " + result.getSize()); // data number per page

        System.out.println("has next page?: " + result.hasNext()); // existence of next page
        System.out.println("first page?: " + result.isFirst()); // is start page(0)

        System.out.println("-------------------------------");
        for (Memo memo : result.getContent()){
            System.out.println(memo);
        }
    }

    @Test
    public void testSort(){
        Sort sort1 = Sort.by("mno").descending(); // 역순 정렬
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2); // connect 2 conditions by and
        Pageable pageable = PageRequest.of(0, 10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }

    @Test
    public void testQueryMethods(){
        List<Memo> list = memoRepository.
                findByMnoBetweenOrderByMnoDesc(70L, 80L); //sort desc from 70 to 80

        for (Memo memo : list){
            System.out.println(memo);
        }
    }
}
