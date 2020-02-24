package com.xy.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xieyong
 * @date 2019/12/16 - 12:40
 */

@Api(value = "物资借用", description = "物资借用")
@RestController
@RequestMapping("/borrowController")
public class BorrowController {
}
