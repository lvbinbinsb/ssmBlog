/*添加cookie*/
    function setCookie(name,value,outTime){
        var expdate=new Date();
        var outms=outTime*24*60*60*1000;//过期时间，以天为单位‘1’表示一天
        expdate.setTime(expdate.getTime()+outms);
        var cookieStr=name+"="+escape(value)+";expires="+expdate.toGMTString();
        //escape方法的作用是进行编码，主要防止value中有特殊字符
        document.cookie=cookieStr;
    }
    /*删除cookie
        cookie的删除并不是物理意义上的直接删除，
        而是将cookie的有效期设置为失效，然后由浏览器删除失效的cookie删除
    */
    function deleteCookie(cookiename){
        var date = new Date();
        var outTime=date.getTime()-1000;//将cookie的有效期设置为失效
        date.setTime(outTime);
        document.cookie=cookiename+"='';expires="+date.toGMTString();
    }
    /*读取cookie*/
    function getCookie(cookieName){
        var cookieStr=document.cookie;
        var cookievalue="";
        if(cookieStr!=null &&cookieStr!=undefined){
            var arrayCookie=cookieStr.split(';');
            for(var i=0;i<arrayCookie.length;i++){
                    var arrayDetail=arrayCookie[i].split('=');
                    if(i==0){
                        cookiMap='{"'+arrayDetail[0]+'":"'+arrayDetail[1]+'",';
                    }else if(i==arrayCookie.length-1){
                        cookiMap+='"'+arrayDetail[0]+'":"'+arrayDetail[1]+'"}';
                    }else{
                        cookiMap+='"'+arrayDetail[0]+'":"'+arrayDetail[1]+'",';
                    }
                }
            }
            var s=cookiMap.replace(/\s/g,"");//去掉字符串中空格
            var cookieObj=JSON.parse(s);
        for(var item in cookieObj){
            if(item==cookieName){
                cookievalue=unescape(cookieObj[item]);
            }
        }
        return cookievalue;
    }
