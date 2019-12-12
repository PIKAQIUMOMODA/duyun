(function($){  
    //备份jquery的ajax方法  
    var _ajax = $.ajax;
      
    //重写jquery的ajax方法  
    $.ajax = function(opt) {
        //备份opt中error和success方法  
        var fn = {  
            error: function(XMLHttpRequest, textStatus, errorThrown) {},
            success: function(data, textStatus) {}
        };

        if(opt.error) {
            fn.error = opt.error;
        }  
        if(opt.success) {
            fn.success = opt.success;
        }

        //扩展增强处理  
        var _opt = $.extend(opt, {
            success: function(data, textStatus) {
                //成功回调检查是否超时
                console.log("check session out");
            	checkSessionOut(data);
                fn.success(data, textStatus);  
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("status:" + textStatus + "\nerror:" + errorThrown);
                fn.error(XMLHttpRequest, textStatus, errorThrown);
            }
        });

        return $.Deferred(function() {
            var _def = this;
            _ajax.call(this, _opt).done(function( data, textStatus, jqXHR ) {
                _def.resolve( data, textStatus, jqXHR );
            }).fail(function() {
                _def.reject();
            })
        })
    };
})(jQuery);

function checkSessionOut(data) {
	var o = typeof data == 'string' ? $.parseJSON(data) : data;
	if(o.sessionState == 0) {
        top.location = "/web/index.html";
	}
}

/*$.getUrlParam = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}*/

