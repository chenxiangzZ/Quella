var studenttable1 = $("[name='str3']").attr('id');
var studenttable2 = $("[name='str3']").attr('lay-filter');
var indexTpl1 = $("[name='str1']").attr('id');
var add1 = $("[name='studentadd']").attr('id');
var barDemo1 = $("[name='str2']").attr('id');
var search1 = $("[name='search']").attr('id');

//监听搜索按钮点击事件
function search() {
    var table = layui.table;
    var search = $('#'+search1).val();

    console.log(search);
    console.log(search);

    table.reload(studenttable1, {
        where: { //设定异步数据接口的额外参数，任意设
            search: search
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}

//表格渲染事件，自动加载
layui.use([ 'layer', 'table'], function () {

    var layer = layui.layer, table = layui.table;
    //让层自适应iframe
    $('#'+add1).on('click', function () {
        var index = layer.open({
            type: 2,
            content: baseUrl+'/student/goAdd',
            area: ['600px', '600px'],
            maxmin: true,
            end: function () {
                table.reload(studenttable1, {});
            }
        });
        parent.layer.iframeAuto(index);
    });
    //LayUi使用表格的方式-表格渲染
    table.render({
        elem: '#'+studenttable1
        , url: baseUrl+'/student/getStudentPage'   //数据接口，需要JSON格式的数据？
        , method: 'post'
        , page: {layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']}
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[        //表头
            {field: '', align: 'center', width: 70, title: '序号', toolbar: '#'+indexTpl1}
            , {field: 'stuName', title: '学生姓名'}
            , {field: 'stuAge', title: '学生年龄'}
            , {field: 'stuGrade', title: '学生成绩'}
            , {field: 'stuNumber', title: '学生学号'}
            , {field: 'stuCreateTime', title: '创建日期'}
            , {field: 'right', align: 'center', width: 150, toolbar: '#'+barDemo1, title: '操作'}  //toolbar -绑定工具条模板
        ]]
    });

    //监听修改按钮
    table.on('tool('+studenttable2+')', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            // 编辑
            var index = layer.open({
                type: 2,
                content: baseUrl+'/student/goUpdate?id=' + data.id,
                area: ['800px', '500px'],
                maxmin: true,
                end: function () {
                    table.reload(studenttable1, {});  //刷新表格
                }
            });
            parent.layer.iframeAuto(index);

        } else if (obj.event === 'del') {
            layer.confirm('真的要删除么？', function (index) {
                // 写删除方法
                $.post(baseUrl+"/student/del", {"id": data.id}, function (data) {
                    if (data.status == 200) {
                        layer.msg(data.message, {icon: 1, time: 1000});
                        // 前端修改
                        layer.close(index);
                        table.reload(studenttable1, {});
                    } else {
                        layer.msg(data.message, {icon: 0, time: 1000});
                        layer.close(index);
                    }
                });
            });
        }
    });

});


