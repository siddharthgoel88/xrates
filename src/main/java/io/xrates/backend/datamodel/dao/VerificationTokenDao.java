package io.xrates.backend.datamodel.dao;

import java.util.Date;
import io.xrates.backend.datamodel.beans.VerificationToken;

public interface VerificationTokenDao extends Dao<VerificationToken>{
    public Date calculateTokenExpiryDate();
}
