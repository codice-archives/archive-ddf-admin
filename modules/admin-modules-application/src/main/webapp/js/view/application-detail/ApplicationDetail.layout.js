/**
 * Copyright (c) Codice Foundation
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details. A copy of the GNU Lesser General Public License
 * is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 *
 **/
/*global define*/
define([
    'backbone',
    'marionette',
    'icanhaz',
    'js/application',
    'text!applicationDetailLayout'
], function(Backbone, Marionette, ich, Application, applicationDetailLayout) {
    "use strict";

    ich.addTemplate('applicationDetailLayout', applicationDetailLayout);

    var App = Application.App;

    var DetailedApplicationLayout = Marionette.Layout.extend({
        template: 'applicationDetailLayout',
        regions: {
            content: '.content'
        },
        events: {
            'click .nav-to-applications': 'navToApplications'
        },
        onRender: function(){
            console.log(App.module('Applications'));  // use this object to get reference to module.controllers
        },
        navToApplications: function(e){
            e.preventDefault();
            App.vent.trigger('navigateTo:applicationHome');
        }
    });

    return DetailedApplicationLayout;

});
