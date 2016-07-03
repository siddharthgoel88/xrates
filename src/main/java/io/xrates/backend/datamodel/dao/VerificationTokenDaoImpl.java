package io.xrates.backend.datamodel.dao;

import io.xrates.backend.datamodel.beans.VerificationToken;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class VerificationTokenDaoImpl extends AbstractDao<VerificationToken> implements VerificationTokenDao {
    private static final int TOKENEXPIRATION = 60 * 24;

    public Date calculateTokenExpiryDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(calendar.MINUTE, TOKENEXPIRATION);
        return new Date(calendar.getTime().getTime());
    }


}
