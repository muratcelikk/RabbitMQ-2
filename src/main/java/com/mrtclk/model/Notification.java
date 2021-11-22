package com.mrtclk.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Murat Çelik on Kas, 2021
 */
@Getter
@Setter
@ToString
public class Notification implements Serializable {

    private String notificationId;
    private Date createdAt;
    private Boolean seen;
    private String message;
}
