package eu.faircode.email;

/*
    This file is part of FairEmail.

    FairEmail is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FairEmail is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with FairEmail.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2018-2022 by Marcel Bokhorst (M66B)
*/

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoCertificate {
    @Query("SELECT * FROM certificate")
    List<EntityCertificate> getCertificates();

    @Query("SELECT * FROM certificate" +
            " ORDER BY intermediate, email, subject")
    LiveData<List<EntityCertificate>> liveCertificates();

    @Query("SELECT * FROM certificate" +
            " WHERE fingerprint = :fingerprint" +
            " AND email = :email COLLATE NOCASE")
    EntityCertificate getCertificate(String fingerprint, String email);

    @Query("SELECT * FROM certificate" +
            " WHERE email = :email COLLATE NOCASE")
    List<EntityCertificate> getCertificateByEmail(String email);

    @Query("SELECT * FROM certificate" +
            " WHERE intermediate")
    List<EntityCertificate> getIntermediateCertificate();

    @Insert
    long insertCertificate(EntityCertificate certificate);

    @Query("DELETE FROM certificate WHERE id = :id")
    void deleteCertificate(long id);
}
