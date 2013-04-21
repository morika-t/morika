package org.example

class Music {
    String title
    Integer bpm
    byte[] jacket
    static mapping ={
        jacket( type:'materialized_blob' )
    }
    static constraints = {
      title( blank: false )
    }
}
