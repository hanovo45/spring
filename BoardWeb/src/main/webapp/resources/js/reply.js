/**
 *  reply.js
 */

console.log("start");

var replyService = (function() {
    
    var sum = function(a,b){
        return a + b;
    };

    // 등록
    function add(reply={rno:10, bno:300, reply:'댓글내용',replyer:'user01'}, callback) {
        $.ajax({
            type: 'post',
            url: '/replies/new',
            data: JSON.stringify(reply),    // {"rno":10, "bno":300, "reply":댓글내용,.....}
            contentType: 'application/json',
            success:function(result){
                if(callback){
                    callback(result);
                }
                console.log(result);
            },
            error: function(reject){
                console.error(reject);
            }
        })
    }   // end of 등록 

    // 글번호 -> 댓글정보 보여줌
    
    function getList(param={}, callback, error) {
        // param = {bno:300}
        var bno = param.bno;
        var page = param.page;
        $.getJSON('/replies/pages/' + bno + '/' + page + '.json', function(data){
            if(callback){
                // console.log(data)
                callback(data);
            }            
        }).fail(function (err){
            if(error){
                // console.log(err);
                error(err);
            }            
        });
    }
    
    // 삭제 
    function remove(rno, callback, error){
        $.ajax({
            method: 'delete',
            url: '/replies/' + rno,
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(reject){
                if(error){
                    error(reject);
                }
            }
        })
    }   // end of remove

    // 업데이트
    function update(reply={}, callback, error){
        $.ajax({
        method:'put',
            url: '/replies/' + reply.rno,
            data: JSON.stringify(reply),
            contentType: 'application/json;charset=UTF-8',
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(reject){
                if(error){
                    error(reject);
                }
            }
        })
    }   // end of update

    // 단건조회
    function get(rno, callback, error){
        $.get('/replies/' + rno + '.json', function(result){
            if(callback){
                callback(result);
            }
        }).fail(function(reject){
            if(error){
                error(reject);
            }
        })
    }   // 단건조회 끝

    // 날짜 표시 
    function displayTime(timeValue){
        // 현재시간을 기준으로 24시간 날짜보여주고
        // 24시간 이내는 시간을 보여준다
        var today = new Date();
        var gap = today.getTime() - timeValue;  // 168093801000
        var dateObj = new Date(timeValue); // getFullYear, getMonth
        if(gap < (1000 * 60 * 60 * 24)){
            var hh = dateObj.getHours();
            var mm = dateObj.getMinutes();
            var ss = dateObj.getSeconds();
            // 14:23:17
            return [(hh>9?'':'0')+hh, ':', (mm>9?'':'0')+mm, ':',(ss>9?'':'0')+ss].join('');     // 배열값 -> 문자열
        }else{
            var yy = dateObj.getFullYear();
            var MM = dateObj.getMonth()+1;
            var dd = dateObj.getDate();
            return [yy, '/', (MM>9?'':'0')+MM, '/', (dd>9?'':'0')+dd].join('');
        }
    }

    return {
        sum: sum,
        add: add,
        getList: getList,
        remove: remove,
        update: update,
        get: get,
        displayTime : displayTime
    }
})() ;

// var reply = {bno:300, reply: 'ajax통한 댓글', replyer:'user00'}
// replyService.add(reply, function(result){
//     alert("Result:" + result)
// });