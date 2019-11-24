package com.surajgautam.demo.controller;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Suraj Gautam.
 */

@Getter
@Setter
public class PageResource<T> {

   private int pageNumber;
   private long totalElements;
   private List<T> content;

}
