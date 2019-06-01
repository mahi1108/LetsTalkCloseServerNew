package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_mLabsCloseServer_LetsTalk_models_UserRealmProxy extends com.mLabsCloseServer.LetsTalk.models.User
    implements RealmObjectProxy, com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface {

    static final class UserColumnInfo extends ColumnInfo {
        long nameInPhoneIndex;
        long idIndex;
        long nameIndex;
        long statusIndex;
        long imageIndex;
        long deptIndex;
        long authenticationIndex;
        long isAdminIndex;
        long contactNameIndex;
        long orgNameIndex;

        UserColumnInfo(OsSchemaInfo schemaInfo) {
            super(10);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("User");
            this.nameInPhoneIndex = addColumnDetails("nameInPhone", "nameInPhone", objectSchemaInfo);
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", "name", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", "status", objectSchemaInfo);
            this.imageIndex = addColumnDetails("image", "image", objectSchemaInfo);
            this.deptIndex = addColumnDetails("dept", "dept", objectSchemaInfo);
            this.authenticationIndex = addColumnDetails("authentication", "authentication", objectSchemaInfo);
            this.isAdminIndex = addColumnDetails("isAdmin", "isAdmin", objectSchemaInfo);
            this.contactNameIndex = addColumnDetails("contactName", "contactName", objectSchemaInfo);
            this.orgNameIndex = addColumnDetails("orgName", "orgName", objectSchemaInfo);
        }

        UserColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new UserColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final UserColumnInfo src = (UserColumnInfo) rawSrc;
            final UserColumnInfo dst = (UserColumnInfo) rawDst;
            dst.nameInPhoneIndex = src.nameInPhoneIndex;
            dst.idIndex = src.idIndex;
            dst.nameIndex = src.nameIndex;
            dst.statusIndex = src.statusIndex;
            dst.imageIndex = src.imageIndex;
            dst.deptIndex = src.deptIndex;
            dst.authenticationIndex = src.authenticationIndex;
            dst.isAdminIndex = src.isAdminIndex;
            dst.contactNameIndex = src.contactNameIndex;
            dst.orgNameIndex = src.orgNameIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private UserColumnInfo columnInfo;
    private ProxyState<com.mLabsCloseServer.LetsTalk.models.User> proxyState;

    com_mLabsCloseServer_LetsTalk_models_UserRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UserColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.mLabsCloseServer.LetsTalk.models.User>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$nameInPhone() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameInPhoneIndex);
    }

    @Override
    public void realmSet$nameInPhone(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameInPhoneIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameInPhoneIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameInPhoneIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameInPhoneIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$status() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.statusIndex);
    }

    @Override
    public void realmSet$status(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.statusIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.statusIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.statusIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$image() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.imageIndex);
    }

    @Override
    public void realmSet$image(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.imageIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.imageIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.imageIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.imageIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$dept() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.deptIndex);
    }

    @Override
    public void realmSet$dept(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.deptIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.deptIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.deptIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.deptIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Boolean realmGet$authentication() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.authenticationIndex)) {
            return null;
        }
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.authenticationIndex);
    }

    @Override
    public void realmSet$authentication(Boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.authenticationIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setBoolean(columnInfo.authenticationIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.authenticationIndex);
            return;
        }
        proxyState.getRow$realm().setBoolean(columnInfo.authenticationIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Boolean realmGet$isAdmin() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.isAdminIndex)) {
            return null;
        }
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isAdminIndex);
    }

    @Override
    public void realmSet$isAdmin(Boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.isAdminIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setBoolean(columnInfo.isAdminIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.isAdminIndex);
            return;
        }
        proxyState.getRow$realm().setBoolean(columnInfo.isAdminIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$contactName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.contactNameIndex);
    }

    @Override
    public void realmSet$contactName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.contactNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.contactNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.contactNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.contactNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$orgName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.orgNameIndex);
    }

    @Override
    public void realmSet$orgName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.orgNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.orgNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.orgNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.orgNameIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("User", 10, 0);
        builder.addPersistedProperty("nameInPhone", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("status", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("image", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("dept", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("authentication", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("isAdmin", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("contactName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("orgName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static UserColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new UserColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "User";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "User";
    }

    @SuppressWarnings("cast")
    public static com.mLabsCloseServer.LetsTalk.models.User createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.mLabsCloseServer.LetsTalk.models.User obj = null;
        if (update) {
            Table table = realm.getTable(com.mLabsCloseServer.LetsTalk.models.User.class);
            UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.mLabsCloseServer.LetsTalk.models.User.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("id")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.mLabsCloseServer.LetsTalk.models.User.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_mLabsCloseServer_LetsTalk_models_UserRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_mLabsCloseServer_LetsTalk_models_UserRealmProxy) realm.createObjectInternal(com.mLabsCloseServer.LetsTalk.models.User.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_mLabsCloseServer_LetsTalk_models_UserRealmProxy) realm.createObjectInternal(com.mLabsCloseServer.LetsTalk.models.User.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface objProxy = (com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) obj;
        if (json.has("nameInPhone")) {
            if (json.isNull("nameInPhone")) {
                objProxy.realmSet$nameInPhone(null);
            } else {
                objProxy.realmSet$nameInPhone((String) json.getString("nameInPhone"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                objProxy.realmSet$status(null);
            } else {
                objProxy.realmSet$status((String) json.getString("status"));
            }
        }
        if (json.has("image")) {
            if (json.isNull("image")) {
                objProxy.realmSet$image(null);
            } else {
                objProxy.realmSet$image((String) json.getString("image"));
            }
        }
        if (json.has("dept")) {
            if (json.isNull("dept")) {
                objProxy.realmSet$dept(null);
            } else {
                objProxy.realmSet$dept((String) json.getString("dept"));
            }
        }
        if (json.has("authentication")) {
            if (json.isNull("authentication")) {
                objProxy.realmSet$authentication(null);
            } else {
                objProxy.realmSet$authentication((boolean) json.getBoolean("authentication"));
            }
        }
        if (json.has("isAdmin")) {
            if (json.isNull("isAdmin")) {
                objProxy.realmSet$isAdmin(null);
            } else {
                objProxy.realmSet$isAdmin((boolean) json.getBoolean("isAdmin"));
            }
        }
        if (json.has("contactName")) {
            if (json.isNull("contactName")) {
                objProxy.realmSet$contactName(null);
            } else {
                objProxy.realmSet$contactName((String) json.getString("contactName"));
            }
        }
        if (json.has("orgName")) {
            if (json.isNull("orgName")) {
                objProxy.realmSet$orgName(null);
            } else {
                objProxy.realmSet$orgName((String) json.getString("orgName"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.mLabsCloseServer.LetsTalk.models.User createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.mLabsCloseServer.LetsTalk.models.User obj = new com.mLabsCloseServer.LetsTalk.models.User();
        final com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface objProxy = (com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("nameInPhone")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nameInPhone((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$nameInPhone(null);
                }
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$status(null);
                }
            } else if (name.equals("image")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$image((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$image(null);
                }
            } else if (name.equals("dept")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$dept((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$dept(null);
                }
            } else if (name.equals("authentication")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$authentication((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$authentication(null);
                }
            } else if (name.equals("isAdmin")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$isAdmin((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$isAdmin(null);
                }
            } else if (name.equals("contactName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$contactName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$contactName(null);
                }
            } else if (name.equals("orgName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$orgName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$orgName(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        return realm.copyToRealm(obj);
    }

    public static com.mLabsCloseServer.LetsTalk.models.User copyOrUpdate(Realm realm, com.mLabsCloseServer.LetsTalk.models.User object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.mLabsCloseServer.LetsTalk.models.User) cachedRealmObject;
        }

        com.mLabsCloseServer.LetsTalk.models.User realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.mLabsCloseServer.LetsTalk.models.User.class);
            UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.mLabsCloseServer.LetsTalk.models.User.class);
            long pkColumnIndex = columnInfo.idIndex;
            String value = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$id();
            long rowIndex = Table.NO_MATCH;
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, value);
            }
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.mLabsCloseServer.LetsTalk.models.User.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_mLabsCloseServer_LetsTalk_models_UserRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.mLabsCloseServer.LetsTalk.models.User copy(Realm realm, com.mLabsCloseServer.LetsTalk.models.User newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.mLabsCloseServer.LetsTalk.models.User) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.mLabsCloseServer.LetsTalk.models.User realmObject = realm.createObjectInternal(com.mLabsCloseServer.LetsTalk.models.User.class, ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface realmObjectSource = (com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) newObject;
        com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface realmObjectCopy = (com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$nameInPhone(realmObjectSource.realmGet$nameInPhone());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectCopy.realmSet$image(realmObjectSource.realmGet$image());
        realmObjectCopy.realmSet$dept(realmObjectSource.realmGet$dept());
        realmObjectCopy.realmSet$authentication(realmObjectSource.realmGet$authentication());
        realmObjectCopy.realmSet$isAdmin(realmObjectSource.realmGet$isAdmin());
        realmObjectCopy.realmSet$contactName(realmObjectSource.realmGet$contactName());
        realmObjectCopy.realmSet$orgName(realmObjectSource.realmGet$orgName());
        return realmObject;
    }

    public static long insert(Realm realm, com.mLabsCloseServer.LetsTalk.models.User object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.mLabsCloseServer.LetsTalk.models.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.mLabsCloseServer.LetsTalk.models.User.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$id();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$nameInPhone = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$nameInPhone();
        if (realmGet$nameInPhone != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameInPhoneIndex, rowIndex, realmGet$nameInPhone, false);
        }
        String realmGet$name = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$status = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        }
        String realmGet$image = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$image();
        if (realmGet$image != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
        }
        String realmGet$dept = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$dept();
        if (realmGet$dept != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.deptIndex, rowIndex, realmGet$dept, false);
        }
        Boolean realmGet$authentication = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$authentication();
        if (realmGet$authentication != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.authenticationIndex, rowIndex, realmGet$authentication, false);
        }
        Boolean realmGet$isAdmin = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$isAdmin();
        if (realmGet$isAdmin != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.isAdminIndex, rowIndex, realmGet$isAdmin, false);
        }
        String realmGet$contactName = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$contactName();
        if (realmGet$contactName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contactNameIndex, rowIndex, realmGet$contactName, false);
        }
        String realmGet$orgName = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$orgName();
        if (realmGet$orgName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.orgNameIndex, rowIndex, realmGet$orgName, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.mLabsCloseServer.LetsTalk.models.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.mLabsCloseServer.LetsTalk.models.User.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.mLabsCloseServer.LetsTalk.models.User object = null;
        while (objects.hasNext()) {
            object = (com.mLabsCloseServer.LetsTalk.models.User) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$id();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$nameInPhone = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$nameInPhone();
            if (realmGet$nameInPhone != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameInPhoneIndex, rowIndex, realmGet$nameInPhone, false);
            }
            String realmGet$name = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            String realmGet$status = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
            }
            String realmGet$image = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$image();
            if (realmGet$image != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
            }
            String realmGet$dept = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$dept();
            if (realmGet$dept != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.deptIndex, rowIndex, realmGet$dept, false);
            }
            Boolean realmGet$authentication = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$authentication();
            if (realmGet$authentication != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.authenticationIndex, rowIndex, realmGet$authentication, false);
            }
            Boolean realmGet$isAdmin = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$isAdmin();
            if (realmGet$isAdmin != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isAdminIndex, rowIndex, realmGet$isAdmin, false);
            }
            String realmGet$contactName = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$contactName();
            if (realmGet$contactName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.contactNameIndex, rowIndex, realmGet$contactName, false);
            }
            String realmGet$orgName = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$orgName();
            if (realmGet$orgName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.orgNameIndex, rowIndex, realmGet$orgName, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.mLabsCloseServer.LetsTalk.models.User object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.mLabsCloseServer.LetsTalk.models.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.mLabsCloseServer.LetsTalk.models.User.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$id();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$nameInPhone = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$nameInPhone();
        if (realmGet$nameInPhone != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameInPhoneIndex, rowIndex, realmGet$nameInPhone, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameInPhoneIndex, rowIndex, false);
        }
        String realmGet$name = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$status = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
        }
        String realmGet$image = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$image();
        if (realmGet$image != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.imageIndex, rowIndex, false);
        }
        String realmGet$dept = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$dept();
        if (realmGet$dept != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.deptIndex, rowIndex, realmGet$dept, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.deptIndex, rowIndex, false);
        }
        Boolean realmGet$authentication = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$authentication();
        if (realmGet$authentication != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.authenticationIndex, rowIndex, realmGet$authentication, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.authenticationIndex, rowIndex, false);
        }
        Boolean realmGet$isAdmin = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$isAdmin();
        if (realmGet$isAdmin != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.isAdminIndex, rowIndex, realmGet$isAdmin, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.isAdminIndex, rowIndex, false);
        }
        String realmGet$contactName = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$contactName();
        if (realmGet$contactName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contactNameIndex, rowIndex, realmGet$contactName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.contactNameIndex, rowIndex, false);
        }
        String realmGet$orgName = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$orgName();
        if (realmGet$orgName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.orgNameIndex, rowIndex, realmGet$orgName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.orgNameIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.mLabsCloseServer.LetsTalk.models.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.mLabsCloseServer.LetsTalk.models.User.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.mLabsCloseServer.LetsTalk.models.User object = null;
        while (objects.hasNext()) {
            object = (com.mLabsCloseServer.LetsTalk.models.User) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$id();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$nameInPhone = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$nameInPhone();
            if (realmGet$nameInPhone != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameInPhoneIndex, rowIndex, realmGet$nameInPhone, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameInPhoneIndex, rowIndex, false);
            }
            String realmGet$name = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            String realmGet$status = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
            }
            String realmGet$image = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$image();
            if (realmGet$image != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.imageIndex, rowIndex, false);
            }
            String realmGet$dept = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$dept();
            if (realmGet$dept != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.deptIndex, rowIndex, realmGet$dept, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.deptIndex, rowIndex, false);
            }
            Boolean realmGet$authentication = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$authentication();
            if (realmGet$authentication != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.authenticationIndex, rowIndex, realmGet$authentication, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.authenticationIndex, rowIndex, false);
            }
            Boolean realmGet$isAdmin = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$isAdmin();
            if (realmGet$isAdmin != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isAdminIndex, rowIndex, realmGet$isAdmin, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.isAdminIndex, rowIndex, false);
            }
            String realmGet$contactName = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$contactName();
            if (realmGet$contactName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.contactNameIndex, rowIndex, realmGet$contactName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.contactNameIndex, rowIndex, false);
            }
            String realmGet$orgName = ((com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) object).realmGet$orgName();
            if (realmGet$orgName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.orgNameIndex, rowIndex, realmGet$orgName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.orgNameIndex, rowIndex, false);
            }
        }
    }

    public static com.mLabsCloseServer.LetsTalk.models.User createDetachedCopy(com.mLabsCloseServer.LetsTalk.models.User realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.mLabsCloseServer.LetsTalk.models.User unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.mLabsCloseServer.LetsTalk.models.User();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.mLabsCloseServer.LetsTalk.models.User) cachedObject.object;
            }
            unmanagedObject = (com.mLabsCloseServer.LetsTalk.models.User) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface unmanagedCopy = (com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) unmanagedObject;
        com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface realmSource = (com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$nameInPhone(realmSource.realmGet$nameInPhone());
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$image(realmSource.realmGet$image());
        unmanagedCopy.realmSet$dept(realmSource.realmGet$dept());
        unmanagedCopy.realmSet$authentication(realmSource.realmGet$authentication());
        unmanagedCopy.realmSet$isAdmin(realmSource.realmGet$isAdmin());
        unmanagedCopy.realmSet$contactName(realmSource.realmGet$contactName());
        unmanagedCopy.realmSet$orgName(realmSource.realmGet$orgName());

        return unmanagedObject;
    }

    static com.mLabsCloseServer.LetsTalk.models.User update(Realm realm, com.mLabsCloseServer.LetsTalk.models.User realmObject, com.mLabsCloseServer.LetsTalk.models.User newObject, Map<RealmModel, RealmObjectProxy> cache) {
        com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface realmObjectTarget = (com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) realmObject;
        com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface realmObjectSource = (com_mLabsCloseServer_LetsTalk_models_UserRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$nameInPhone(realmObjectSource.realmGet$nameInPhone());
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectTarget.realmSet$image(realmObjectSource.realmGet$image());
        realmObjectTarget.realmSet$dept(realmObjectSource.realmGet$dept());
        realmObjectTarget.realmSet$authentication(realmObjectSource.realmGet$authentication());
        realmObjectTarget.realmSet$isAdmin(realmObjectSource.realmGet$isAdmin());
        realmObjectTarget.realmSet$contactName(realmObjectSource.realmGet$contactName());
        realmObjectTarget.realmSet$orgName(realmObjectSource.realmGet$orgName());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("User = proxy[");
        stringBuilder.append("{nameInPhone:");
        stringBuilder.append(realmGet$nameInPhone() != null ? realmGet$nameInPhone() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status() != null ? realmGet$status() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{image:");
        stringBuilder.append(realmGet$image() != null ? realmGet$image() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dept:");
        stringBuilder.append(realmGet$dept() != null ? realmGet$dept() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{authentication:");
        stringBuilder.append(realmGet$authentication() != null ? realmGet$authentication() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isAdmin:");
        stringBuilder.append(realmGet$isAdmin() != null ? realmGet$isAdmin() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{contactName:");
        stringBuilder.append(realmGet$contactName() != null ? realmGet$contactName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{orgName:");
        stringBuilder.append(realmGet$orgName() != null ? realmGet$orgName() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

}
