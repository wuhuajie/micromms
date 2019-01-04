//namespace dubbo_java org.apache.dubbo.rpc.gen.dubbo
//namespace dubbo_cpp  org.apache.dubbo.rpc.gen.dubbo

namespace java org.apache.dubbo.rpc.gen.thrift
namespace cpp  org.apache.dubbo.rpc.gen.thrift

struct DemoStruct {
 1: required string group //所属组
 2: required string group2 //所属组

}

struct DemoData {
 1: required string group //所属组
 2: required string group2 //所属组
 3: required DemoStruct demoStruct //所属组
}

struct Verify {
  1: optional string id;
  2: required string code;
  3: optional string mobile;
  4: optional string email;
  5: required i64 validTime;
  6: optional string emailSign;
  7: optional string uid;
  8: optional string itu;
}

service Demo {
    bool echoBool( 1:required bool arg );
    byte echoByte( 1:required byte arg );
    i16  echoI16 ( 1:required i16  arg );
    i32  echoI32 ( 1:required i32  arg );
    i64  echoI64 ( 1:required i64  arg );

    double echoDouble( 1:required double arg );
    string echoString( 1:required string arg );

    Verify echoDemoData( 1:required DemoData arg );

}

