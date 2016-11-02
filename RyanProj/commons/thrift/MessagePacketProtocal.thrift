namespace java com.hyron.commons.packet

/* ======================================================
 * 常量定义
 * ====================================================== */
/** 包版本号 **/
const byte PACKET_VERSION = 0x01;

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

/** 消息包 */
struct MessagePacket {
    /** json 数据*/
    1: optional string appKey,

    /** 扩展字段*/
    2: optional map<string,string> extMap,
}