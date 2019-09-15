ALTER SESSION SET CURRENT_SCHEMA ="PROD2_OCS";

SELECT DISTINCT
           'update REVISIONS set DRELEASESTATE = ''N'' where did = ' || r.did  ||';'
        
        FROM
           revisions r,
           docmeta d,
           (
               SELECT DISTINCT
                   d.xdrequestid,
                   d.xdbusinessunit,
                   d.xreviewedby,
                   d.xidcprofile
               FROM
                   revisions r,
                   docmeta d
               WHERE
                   1 = 1
                   AND r.dstatus != 'EXPIRED'
                   AND d.did = r.did
                   AND d.xdapplication = 'MARS'
                   AND d.xidcprofile IN ('dell_mars_folio')
           ) form
        WHERE
           1 = 1
           AND d.did = r.did
           AND d.xidcprofile IN ('dell_mars_attachment')
           AND ( d.xreviewedby IS NULL
                 OR d.xdbusinessunit IS NULL )
           AND form.xdrequestid = d.xdrequestid
           AND d.xdapplication = 'MARS'
           AND r.dstatus != 'EXPIRED';
           

MERGE INTO docmeta doc USING (
        SELECT DISTINCT
           r.did,
           r.ddocname,
           form.xdbusinessunit,
           form.xreviewedby
        FROM
           revisions r,
           docmeta d,
           (
               SELECT DISTINCT
                   d.xdrequestid,
                   d.xdbusinessunit,
                   d.xreviewedby,
                   d.xidcprofile
               FROM
                   revisions r,
                   docmeta d
               WHERE
                   1 = 1
                   AND r.dstatus != 'EXPIRED'
                   AND d.did = r.did
                   AND d.xdapplication = 'MARS'
                   AND d.xidcprofile IN ('dell_mars_folio')
           ) form
        WHERE
           1 = 1
           AND d.did = r.did
           AND d.xidcprofile IN ('dell_mars_attachment')
           AND ( d.xreviewedby IS NULL
                 OR d.xdbusinessunit IS NULL )
           AND form.xdrequestid = d.xdrequestid
           AND d.xdapplication = 'MARS'
           AND r.dstatus != 'EXPIRED'
        )
s ON ( doc.did = s.did )
WHEN MATCHED THEN 
UPDATE 
    SET 
        doc.xdbusinessunit = s.xdbusinessunit,
        doc.xreviewedby = s.xreviewedby
;


           
           
