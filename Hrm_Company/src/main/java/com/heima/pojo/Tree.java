package com.heima.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tree {
    private String id;
    private String pid;
    private List<Tree> ls = new ArrayList<>();
    private String name;
}
