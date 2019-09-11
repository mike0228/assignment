package com.example.assignment.cache;

import com.example.assignment.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get(){
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program =new TagDTO();
        program.setCategoryName("编程");
        program.setTags(Arrays.asList("c/c++","python","java","html","css","php","c#","jsp","mysql"));
        tagDTOS.add(program);
        TagDTO game =new TagDTO();
        game.setCategoryName("游戏");
        game.setTags(Arrays.asList("英雄联盟","王者荣耀","反恐精英","穿越火线"));
        tagDTOS.add(game);
        TagDTO sport =new TagDTO();
        sport.setCategoryName("体育");
        sport.setTags(Arrays.asList("篮球","足球","跳绳","下棋"));
        tagDTOS.add(sport);
        TagDTO delicious =new TagDTO();
        delicious.setCategoryName("美食");
        delicious.setTags(Arrays.asList("章鱼大爆头","肚包脑","大酱肘子","肥肠刺身"));
        tagDTOS.add(delicious);
        return tagDTOS;
    }
    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags,",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag-> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
