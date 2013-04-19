package org.example

class Music {
    String title
    Integer bpm
    byte[] jacket
    static constraints = {
      title( blank: false )
      jacket( type:'materialized_blob',maxSize:10240000 )
    }
}
