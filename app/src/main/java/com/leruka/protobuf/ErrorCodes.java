// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: errorCodes.proto

package com.leruka.protobuf;

public final class ErrorCodes {
  private ErrorCodes() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code leruka.ErrorCode}
   */
  public enum ErrorCode
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>UNKNOWN = 0;</code>
     */
    UNKNOWN(0, 0),
    /**
     * <code>REQUEST_WRONG_CONTENT_TYPE = 101;</code>
     *
     * <pre>
     * Request
     * </pre>
     */
    REQUEST_WRONG_CONTENT_TYPE(1, 101),
    /**
     * <code>REGISTER_NAME_USED = 201;</code>
     *
     * <pre>
     * Register
     * </pre>
     */
    REGISTER_NAME_USED(2, 201),
    /**
     * <code>LOGIN_NAME_UNKNOWN = 301;</code>
     *
     * <pre>
     * Login
     * </pre>
     */
    LOGIN_NAME_UNKNOWN(3, 301),
    /**
     * <code>LOGIN_PASS_WRONG = 302;</code>
     */
    LOGIN_PASS_WRONG(4, 302),
    /**
     * <code>USER_NAME_INVALID = 401;</code>
     *
     * <pre>
     * User
     * </pre>
     */
    USER_NAME_INVALID(5, 401),
    /**
     * <code>USER_PASS_INVALID = 402;</code>
     */
    USER_PASS_INVALID(6, 402),
    /**
     * <code>DB_UNKNOWN_ERROR = 501;</code>
     *
     * <pre>
     * Database
     * </pre>
     */
    DB_UNKNOWN_ERROR(7, 501),
    UNRECOGNIZED(-1, -1),
    ;

    /**
     * <code>UNKNOWN = 0;</code>
     */
    public static final int UNKNOWN_VALUE = 0;
    /**
     * <code>REQUEST_WRONG_CONTENT_TYPE = 101;</code>
     *
     * <pre>
     * Request
     * </pre>
     */
    public static final int REQUEST_WRONG_CONTENT_TYPE_VALUE = 101;
    /**
     * <code>REGISTER_NAME_USED = 201;</code>
     *
     * <pre>
     * Register
     * </pre>
     */
    public static final int REGISTER_NAME_USED_VALUE = 201;
    /**
     * <code>LOGIN_NAME_UNKNOWN = 301;</code>
     *
     * <pre>
     * Login
     * </pre>
     */
    public static final int LOGIN_NAME_UNKNOWN_VALUE = 301;
    /**
     * <code>LOGIN_PASS_WRONG = 302;</code>
     */
    public static final int LOGIN_PASS_WRONG_VALUE = 302;
    /**
     * <code>USER_NAME_INVALID = 401;</code>
     *
     * <pre>
     * User
     * </pre>
     */
    public static final int USER_NAME_INVALID_VALUE = 401;
    /**
     * <code>USER_PASS_INVALID = 402;</code>
     */
    public static final int USER_PASS_INVALID_VALUE = 402;
    /**
     * <code>DB_UNKNOWN_ERROR = 501;</code>
     *
     * <pre>
     * Database
     * </pre>
     */
    public static final int DB_UNKNOWN_ERROR_VALUE = 501;


    public final int getNumber() {
      if (index == -1) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    public static ErrorCode valueOf(int value) {
      switch (value) {
        case 0: return UNKNOWN;
        case 101: return REQUEST_WRONG_CONTENT_TYPE;
        case 201: return REGISTER_NAME_USED;
        case 301: return LOGIN_NAME_UNKNOWN;
        case 302: return LOGIN_PASS_WRONG;
        case 401: return USER_NAME_INVALID;
        case 402: return USER_PASS_INVALID;
        case 501: return DB_UNKNOWN_ERROR;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ErrorCode>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        ErrorCode> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ErrorCode>() {
            public ErrorCode findValueByNumber(int number) {
              return ErrorCode.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.leruka.protobuf.ErrorCodes.getDescriptor().getEnumTypes().get(0);
    }

    private static final ErrorCode[] VALUES = values();

    public static ErrorCode valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ErrorCode(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:leruka.ErrorCode)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020errorCodes.proto\022\006leruka*\310\001\n\tErrorCode" +
      "\022\013\n\007UNKNOWN\020\000\022\036\n\032REQUEST_WRONG_CONTENT_T" +
      "YPE\020e\022\027\n\022REGISTER_NAME_USED\020\311\001\022\027\n\022LOGIN_" +
      "NAME_UNKNOWN\020\255\002\022\025\n\020LOGIN_PASS_WRONG\020\256\002\022\026" +
      "\n\021USER_NAME_INVALID\020\221\003\022\026\n\021USER_PASS_INVA" +
      "LID\020\222\003\022\025\n\020DB_UNKNOWN_ERROR\020\365\003B\025\n\023com.ler" +
      "uka.protobufb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
