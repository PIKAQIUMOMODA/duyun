/**
 * Created by hyc on 2017/5/8.
 */
var createJsPath = function(js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i=0; i<scripts.length; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            path = src.split(js)[0];
            break;
        }
    }
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        var href = location.href;
        href = href.split("#")[0];
        href = href.split("?")[0];
        var strArr = href.split("/");
        strArr.length = strArr.length - 1;
        href = strArr.concat("/");
        path = href + "/" + path;
    }
    return path;
};

var bootPath = createJsPath("common-js.js");

//js
document.write('<script src="' + bootPath + 'hplus/js/jquery.min.js?v=2.1.4"><\/script>');
/*
document.write('<script src="' + bootPath + 'hplus/js/plugins/webuploader/webuploader.min.js"><\/script>');
*/
document.write('<script src="' + bootPath + 'hplus/js/bootstrap.min.js?v=3.3.6"><\/script>');
document.write('<script src="' + bootPath + 'hplus/js/content.min.js?v=1.0.0"><\/script>');
//document.write('<script src="' + bootPath + 'hplus/js/plugins/bootstrap-table/bootstrap-table.js"><\/script>');
document.write('<script src="' + bootPath + 'hplus/js/plugins/bootstrap-table/bootstrap-table.min.js"><\/script>');
document.write('<script src="' + bootPath + 'hplus/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"><\/script>');


/*if(window.localStorage && localStorage.getItem("storageLangue")=='YES'){
  //英文版本的话用下面这个js*/
  document.write('<script src="' + bootPath + 'hplus/js/plugins/bootstrap-table/locale/bootstrap-table-en-US.min.js"><\/script>');
/*}else{
  document.write('<script src="' + bootPath + 'hplus/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"><\/script>');
}*/
document.write('<script src="' + bootPath + 'hplus/js/plugins/validate/jquery.validate.min.js"><\/script>');
document.write('<script src="' + bootPath + 'hplus/js/jquery.i18n.properties.js"><\/script>');
document.write('<script src="' + bootPath + 'hplus/js/plugins/validate/messages_zh.min.js"><\/script>');
document.write('<script src="' + bootPath + 'hplus/js/plugins/layer/laydate/laydate.js"><\/script>');
document.write('<script src="' + bootPath + 'hplus/js/plugins/layer/layer.min.js"><\/script>');
document.write('<script src="' + bootPath + 'hplus/js/plugins/jsTree/jstree.min.js"><\/script>');
document.write('<script src="' + bootPath + 'hplus/js/plugins/chosen/chosen.jquery.js"><\/script>');
/*document.write('<script src="' + bootPath + 'custom/jquery-session.js"><\/script>');*/
document.write('<script src="' + bootPath + 'hplus/js/fileinput.min.js"><\/script>');
/*
document.write('<script src="' + bootPath + 'hplus/js/plugins/staps/jquery.steps.min.js"><\/script>');*/
