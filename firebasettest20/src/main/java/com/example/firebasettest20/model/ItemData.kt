package com.example.firebasettest20.model

class ItemData {
    // docId 파이어 스토어=NOSQL, <-> (mysql, oracle 디비를 저장하는 기능이 비슷한거지, 종류가 다름)
    /*컬렉션(마치 테이블처럼 사용), 문서(마치 행),docId(문서의 번호-> 마치 PK)
    * 문서번호, 자동으로 생성해서 사용중, 물론 임의로 작성가능(유니크 속성 주의해서 작성)*/
    var docId: String? = null
    /*인증이 되면, 해당 이메일이, 인증 객체에 등록이 된다.*/
    var email: String? = null

    var content: String? = null
    var date: String? = null
}