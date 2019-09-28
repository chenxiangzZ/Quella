var articaltable1 = $("[name='str1']").attr('id');
var articaltable2 = $("[name='str1']").attr('lay-filter');
var indextpl2 = $("[name='str2']").attr('id')
var barDemo2 = $("[name='str3']").attr('id')



layui.use(['layer', 'table'], function () {
    var layer = layui.layer, table = layui.table;
    //让层自适应iframe
    //表格渲染
    table.render({
        elem: '#'+articaltable1
        , url: baseUrl+'/artical/getArticalPage'
        , method: 'post'
        , page: {layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']}
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: '', align: 'center', width: 70, title: '序号', toolbar: '#'+indextpl2}
            , {field: 'stuName', title: '学生姓名'}
            , {field: 'stuAge', title: '学生年龄'}
            , {field: 'stuGrade', title: '学生成绩'}
            , {field: 'stuNumber', title: '学生学号'}
            , {field: 'stuCreateTime', title: '创建日期'}
            , {field: 'right', align: 'center', width: 150, toolbar: '#'+barDemo2, title: '操作'}  //toolbar -绑定工具条模板
        ]]
    });

    //监听修改按钮
    table.on('tool('+articaltable2+')', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            // 编辑
            var index = layer.open({
                type: 2,
                content: baseUrl+'/artical/goUpdate?id=' + data.id,
                area: ['800px', '500px'],
                maxmin: true,
                end: function () {
                    table.reload(articaltable1, {});
                }
            });
            parent.layer.iframeAuto(index);

        }
    })

});
