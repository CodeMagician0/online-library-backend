package com.codemagician.onlinelibrary.domain.rsp;

import com.codemagician.onlinelibrary.domain.vo.BookVO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/10/9 13:29
 */

@Data
@AllArgsConstructor
public class CurrentLoansRsp {

    private BookVO book;

    private int daysLeft;
}
