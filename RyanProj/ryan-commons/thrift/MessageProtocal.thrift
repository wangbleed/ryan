namespace java com.uucun.msg.protocol.packets

/* ======================================================
 * 常量定义
 * ====================================================== */
/** 包版本号 **/
const byte PACKET_VERSION = 0x01;

/* ------------------ 指令编号 ---------------- */
/** TCP心跳指令 **/
const byte TCP_KEEPALIVE_COMMAND = 0x00;
/** 注册包指令  **/
const byte REGISTER_COMMAND = 0x01;
/** 注册包回复指令 **/
const byte REGISTER_REPLY_COMMAND = 0x02;
/** KeepAlive包指令 **/
const byte KEEPALIVE_COMMAND = 0x03;
/** KeepAlive包回复指令 **/
const byte KEEPALIVE_REPLY_COMMAND = 0x04;
/** 在线检测包指令  **/
const byte ONLINE_COMMAND = 0x05;
/** 在线检测包回复指令 **/
const byte ONLINE_REPLY_COMMAND = 0x06;
/** 消息包指令  **/
const byte MESSAGE_COMMAND = 0x07;
/** 消息包回复指令 **/
const byte MESSAGE_REPLY_COMMAND = 0x08;
/** 客户端自定义消息包 **/
const byte CUSTOM_MESSAGE_COMMAND = 0x09;
/** 客户端自定义消息包回复 **/
//const byte CUSTOM_MESSAGE_REPLY_COMMAND = 0x0A;
/** 要求重发数据包 **/
const byte REQUEST_RESEND_COMMAND = 0x0B;
/** 请求验证连接 **/
const byte AUTHCONN_COMMAND = 0x0C;
/** 请求验证连接回复 **/
const byte AUTHCONN_REPLY_COMMAND = 0x0D;
/** SDK2注册包指令 **/
const byte SDK2_REGISTER_COMMAND = 0x0E;
/** SDK2注册包回复指令 **/
const byte SDK2_REGISTER_REPLY_COMMAND = 0x0F;
/** SDK2加密包指令 **/
const byte CUSTOM_ENCRYPT_COMMAND = 0x10;

/* -----------------包体结构中extData中的key值定义------------------*/
/** 8G/7G/6G/5G/4G/3G/2G/WIFI ，先预设8位（11111111）也就是最大值255，字符串长度为1位**/
const string EXTDATA_KEY_DELAYSHOWTIME = "E1";



/*------------ 手机网络类型值以及属于2G/3G/4G/WIFI情况 ------------*/
/** 2G */
const byte NETWORK_TYPE_UNKNOW = 0;
/** 2G */
const byte NETWORK_TYPE_GPRS = 1;
/** 2G */
const byte NETWORK_TYPE_EDGE = 2;
/** 3G */
const byte NETWORK_TYPE_UMTS = 3;
/** 2G */
const byte NETWORK_TYPE_CDMA = 4;
/** 3G */
const byte NETWORK_TYPE_EVDO_0 = 5;
/** 3G */
const byte NETWORK_TYPE_EVDO_A = 6;
/** 2G */
const byte NETWORK_TYPE_1xRTT = 7;
/** 3G */
const byte NETWORK_TYPE_HSDPA = 8;
/** 3G */
const byte NETWORK_TYPE_HSUPA = 9;
/** 3G */
const byte NETWORK_TYPE_HSPA = 10;
/** 2G */
const byte NETWORK_TYPE_IDEN = 11;
/** 3G */
const byte NETWORK_TYPE_EVDO_B = 12;
/** 4G */
const byte NETWORK_TYPE_LTE = 13;
/** 3G */
const byte NETWORK_TYPE_EHRPD = 14;
/** 3G */
const byte NETWORK_TYPE_HSPAP = 15;
/** 网络类型中的WIFI类型 **/
const byte NETWORK_TYPE_WIFI = 0x10;

/* ======================================================
 * 指令和
 * ====================================================== */
/** 命令字和消息类映射关系定义 */
const map<byte, string> ORDER_PACKET_DICT = {
    /** TCP心跳指令 **/
    TCP_KEEPALIVE_COMMAND : "TCPKeepAlivePacket",
    /** 注册包指令  **/
    REGISTER_COMMAND : "RegistPacket",
    /** 注册包回复指令 **/
    REGISTER_REPLY_COMMAND : "RegistReplyPacket",
    /** KeepAlive包指令 **/
    KEEPALIVE_COMMAND : "KeepalivePacket",
    /** KeepAlive包回复指令 **/
    KEEPALIVE_REPLY_COMMAND : "KeepaliveReplyPacket",
    /** 在线检测包指令  **/
    ONLINE_COMMAND : "OnlineCheckPacket",
    /** 在线检测包回复指令 **/
    ONLINE_REPLY_COMMAND : "OnlineCheckReplyPacket",
    /** 消息包指令  **/
    MESSAGE_COMMAND : "MessageSetPacket" ,
    /** 消息包回复指令 **/
    MESSAGE_REPLY_COMMAND : "MessageReplyPacket",
    /** 自定义消息包指令  **/
    CUSTOM_MESSAGE_COMMAND : "CustomMessagePacket",
    /** 请求验证连接 **/
    AUTHCONN_COMMAND : "AuthConnPacket",
    /** 请求验证连接回复 **/
    AUTHCONN_REPLY_COMMAND : "AuthConnReplyPacket"
    /** sdk2注册包指令  **/
    SDK2_REGISTER_COMMAND : "sdk2.RegistPacket",
    /** sdk2注册包回复指令 **/
    SDK2_REGISTER_REPLY_COMMAND : "sdk2.RegistReplyPacket",
    /** sdk2加密包指令 **/
    CUSTOM_ENCRYPT_COMMAND : "CustomEncryptPacket",
}


/**
 * 消息包头，说明后接的消息包体的内容和格式，每次发送消息需要首先发送PacketHead
 * 然后发送Packet内容。
 */
struct PacketHead {
    /** 包版本  **/
    1: byte version = PACKET_VERSION,
    
    /** 
     * 包指令，通过对这个位的判断决定后续解析什么数据包  
     */
    2: byte order,
    
    /** 包序列  **/
    3: i32 seq,
}

/** 注册包中的状态字 */
// 新注册
const byte RG_STAT_NEW = 0;
// 安装或者卸载应用导致的注册
const byte RG_STAT_UPDATE = 1;

/**
 * 注册包
 * Note: 通过UDP发送
 */
struct RegistPacket {
  /** 用户唯一标识 */
  1: binary uid,

  /** APK-ID(渠道号) */
  2: optional list<i32> apkids,
  
  /** 
   * 是否为新注册，或者是安装卸载注册
   * 具体值请参考 RG_STAT_NEW / RG_STAT_UPDATE
   */
  3: byte updateState,
}

/**
 * 注册回复包
 * Note: 通过UDP发送
 */
struct RegistReplyPacket {
  1: optional binary data,
}

/**
 * 
 * Note: 通过UDP发送发送的心跳包返回需要连接的IP地址
 */
struct KeepalivePacket {
  /** 用户唯一标识 */
  1: binary uid,
}


/** 心跳包回复状态 */
const byte KA_STATE_OK = 0;
const byte KA_STATE_BUSY = 1;
const byte KA_STATE_SERVER_ERR = 2;
const byte KA_STATE_REG_ERR = 3;

/**
 * 一些网络配置信息
 */
struct Setting {
  
  /** TCP KeepAlive 时间 */
  1: optional i32 tcpKeepAliveTime,
  
  /** 客户端UDP重连时间，如果UDP服务无相应等待这个时间后重试 */
  2: optional i32 udpRetryTime,
}

     
/** 渠道 apkid == channelid*/
const string APPINFO_APKID = "apkid";
/** 应用ID*/
const string APPINFO_APPKEY = "appkey";
/** 应用版本名称*/
const string APPINFO_VERNAME = "versionname";
/** 应用版本代码*/
const string APPINFO_VERCODE = "versioncode";
/** sdk版本*/
const string APPINFO_SDKVERSION= "sdkversion";
const string APPINFO_PUSHID = "pushid";


/** App信息体*/
struct AppInfo {
     
     /** SDK扩展map*/
     1: optional map<string,string> sdkMap,

     /** USER自定义map*/
     2: optional map<string,string> userMap,
}


/**
 * 心跳回复包
 * Note: 通过UDP发送发送的心跳包返回需要连接的IP地址
 */
struct KeepaliveReplyPacket {
  /** 连接标志， 
   * 0：可以连接 
   * 1: 暂时无法连接，稍后重试
   * 2: 发生服务器端错误，或者拒绝连接
   * 3: 没有注册
   */
  1: optional byte state ,
  
  /** 可以连接的MessageAgent服务器地址 */
  2: optional string host ,
  
  /** 可以连接的端口，如果没有设置，就使用缺省端口 */
  3: optional i32 port ,
  
  /** 连接token，用于验证用户身份 */
  4: optional string token,
  
  /** 服务忙重试时间 */
  5: optional i32 retryTime,
  
  /** 配置信息 */
  6: optional Setting setting,
}

/**
 * 验证连接，发送uid和token到服务端
 * Note: 通过TCP发送
 */
struct AuthConnPacket {
  /** 授权token*/
  1: optional string token ,
  
  /** 用户唯一标识*/
  2: optional binary uid,

  /** 网络制式*/
  3: optional byte networkType,

  /** sdk端所有应用信息*/
  4: optional list<AppInfo> appsInfo,
  
  /** 扩展字段*/
  5: optional map<string,string> extMap,
}

/** 心跳包回复状态 */
const byte Auth_STATE_OK = 0;
const byte Auth_STATE_FAIL = 1;


/** 授权回复包中的key值表**/
const string AUTHREPLY_FIRST_RECEIVE = "F1";
const string AUTHREPLY_RECEIVE_INTERVAL = "R1";
const string AUTHREPLY_MAX_RECEIVE = "M1";
const string AUTHREPLY_DAY_INTERVAL = "D1";

/**
 * 验证连接反馈
 * Note: 通过TCP发送
 */
struct AuthConnReplyPacket {
  1: optional byte state,

  /** 扩展字段*/
  2: optional map<string,string> extMap,
}


/** 
 * TCP心跳包，不包含任何内容，只是为了保持网络连接
 */
struct TCPKeepAlivePacket {}


/** 当数据包长度大于400时就进行压缩 **/
const i32 UNGZIP_DATA_LENGTH_MAX = 400;
/** 是否压缩标志位 */
const byte ENABLE_COMPRESSION = 0x01;
/** 是否加密标志位 */
const byte ENABLE_ENCRYPT = 0x02;
/** 关闭加密和压缩 */
const byte DISABLE_ENCRYPT_COMPRESSION = 0x00;
/** 加密并且压缩数据字段 */
const byte ENABLE_ENCRYPT_COMPRESSION = 0x03;
/** 用户ID长度固定为16个字节，是使用UUID算法产生的128bit的唯一值 */
const i32 USER_ID_LENGTH = 16;
/** 最大允许的Packet长度 */
const i32 UDP_PACKET_MAX_LEGHT = 255;
/** 最大允许的消息字符串或者多媒体内容长度，最大为40K*/
const i32 MESSAGE_MAX_LEGHT = 40960; 


/**
 * 消息类型（MessageType）定义，以下是系统使用的，业务方自己定义的消息类型不要与
 * 这里的类型相同。
 */
/** 推送广告消息-AirPush */
const byte MT_AIRPUSH = 0;
/** 文本聊天消息 */
const byte MT_IM = 1;
/** 媒体内容消息 */
const byte MT_MEDIA = 2;
/** 联通Push内容 */
const byte MT_UNIPUSH = 3;
/** 强制显示*/
const string MSG_FORCE_DISPLAY = "FD"
/** 在分配的agent失效时，反复重连statsrv的时间间隔，单位：秒*/
const string SET_RETRY_STATSRV = "SRS"
/** PUSH的优先级字段**/
const string MP_PRIORITY = "P1"
/**
 * 用户新建默认级别	10
 * 秒杀活动		11
 * 分享			12
 * 月末抢流量		13
 * 新增用户		14
 * 沉默用户		15
 * 积分过期		16
 * 流量不足		17
 * 疑似流失用户		18
 */
const byte MP_PRIORITY_DEFAULT = 10

/** 消息包 */
struct MessagePacket {
    /** 应用*/
    1: optional string appKey,

    /** 消息目标APKID */
    2: optional string apkid,
    
    /** 消息类型 */
    3: optional byte messageType,
        
    /** 加密，压缩，编码标志位 */
    4: optional byte encryptCompressionEncode,
    
    /** 文本消息内容,当 messageType == MT_IM 时，这个字段保存消息内容 */
    5: optional string message,
    
    /** 
     * 二进制消息内容,当 
     *   messageType == MT_MEDIA || messageType == MT_AIRPUSH  
     * 时，这个字段保存序列化后的数据内容  
     */
    6: optional binary data,

    /** 扩展字段*/
    7: optional map<string,string> extMap,
}


struct MessageSetPacket {
    /** sdk所在所有应用下所有push*/
    1: optional list<MessagePacket> msgPackets,
}

/** 消息包回复，当sdk接收到后，立即返回给服务器 */ 
struct MessageReplyPacket {

    1: optional binary uid,
    
    /** key--appKey，value--pushID*/
    2: optional map<string,string> pushInfo,

    /** 正确接收的消息编号 */
    3: optional i32 receiveSeq,
}

const string CMP_UID = "U1";
const string CMP_PUSHINFO = "P1";
const string CMP_EXPIRED = "E1"
const string CMP_UPDATE_URL = "U2"
const string CMP_UPDATE_SDK_MD5 = "MD5"
const string CMP_CONTACT = "C1"
const string CMP_APPINFO = "A1"
const string CMP_LOCATION = "L1"
const string CMP_USAGE = "U1" 
/**
* key:{CMP_UID}, value:uid_value
* key:{CMP_PUSHINFO}, value:[{"appkey":"wostore",:"pushid":"xxxxx,xxxxx,xxxx"},{"appkey":"unicom",:"pushid":"xxxxx,xxxxx,xxxx"}]
* key:{CMP_EXPIRED}, value:[{"appkey":"wostore",:"pushid":"xxxxx,xxxxx,xxxx"},{"appkey":"unicom",:"pushid":"xxxxx,xxxxx,xxxx"}]
*/
const byte CMP_APPREPLY = 1;
/*
* key:{CMP_URL}, value:URL_VALUE
*/
const byte CMP_SDKUPDATE = 2;

const byte CMP_EXT_DEVICE_INFO = 3;



/** 自定义包 */ 
struct CustomMessagePacket {
    /** 数据类型*/
    1: optional byte type,
    
    2: optional map<string,string> data,
}

/** 自定义加密包 */ 
struct CustomEncryptPacket {
    /** 用户唯一标识*/
    1: optional binary uid,
    /** 包的类型CMD*/
    2: optional byte order,
    /** 加密数据*/   
    3: optional binary encryptData,
    /** 加密类型*/
    4: optional byte encryptType
}

