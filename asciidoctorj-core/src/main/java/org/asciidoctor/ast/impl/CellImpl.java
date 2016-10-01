package org.asciidoctor.ast.impl;

import org.asciidoctor.ast.Cell;
import org.asciidoctor.ast.Column;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.NodeConverter;
import org.asciidoctor.ast.Table;
import org.jruby.runtime.builtin.IRubyObject;

public class CellImpl extends ContentNodeImpl implements Cell {

    public CellImpl(IRubyObject rubyNode) {
        super(rubyNode);
    }

    @Override
    public Column getColumn() {
        return (Column) getParent();
    }

    @Override
    public int getColspan() {
        return getInt("colspan");
    }

    @Override
    public int getRowspan() {
        return getInt("rowspan");
    }

    @Override
    public String getText() {
        return getString("text");
    }

    @Override
    public Object getContent() {
        return toJava(getRubyProperty("content"));
    }

    @Override
    public String getStyle() {
        return getSymbol("style");
    }

    @Override
    public void setStyle(String style) {
        setSymbol("style", style);
    }

    @Override
    public Table.HorizontalAlignment getHorizontalAlignment() {
        return Table.HorizontalAlignment.valueOf(((String) getAttr("halign", "left")).toUpperCase());
    }

    @Override
    public void setHorizontalAlignment(Table.HorizontalAlignment halign) {
        setAttr("halign", halign.name().toLowerCase(), true);
    }

    @Override
    public Table.VerticalAlignment getVerticalAlignment() {
        return Table.VerticalAlignment.valueOf(((String) getAttr("valign", "top")).toUpperCase());
    }

    @Override
    public void setVerticalAlignment(Table.VerticalAlignment valign) {
        setAttr("valign", valign.name().toLowerCase(), true);
    }

    @Override
    public Document getInnerDocument() {
        IRubyObject innerDocument = getRubyProperty("inner_document");
        if (innerDocument.isNil()) {
        	return null;
        }
		return (Document) NodeConverter.createASTNode(innerDocument);
    }

    @Override
    public void setInnerDocument(Document document) {
        setRubyProperty("@inner_document", ((DocumentImpl) document).getRubyObject());
    }

}