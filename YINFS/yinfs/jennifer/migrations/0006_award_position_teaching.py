# -*- coding: utf-8 -*-
# Generated by Django 1.11 on 2017-04-29 11:47
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('jennifer', '0005_auto_20170429_1331'),
    ]

    operations = [
        migrations.CreateModel(
            name='Award',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('yearStart', models.CharField(max_length=4)),
                ('title', models.CharField(blank=True, max_length=15)),
                ('yearEnd', models.CharField(blank=True, max_length=4)),
                ('name', models.CharField(max_length=50)),
                ('description', models.TextField()),
            ],
            options={
                'abstract': False,
            },
        ),
        migrations.CreateModel(
            name='Position',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('yearStart', models.CharField(max_length=4)),
                ('yearEnd', models.CharField(blank=True, max_length=4)),
                ('name', models.CharField(max_length=50)),
                ('university', models.CharField(max_length=50)),
                ('faculty', models.CharField(max_length=50)),
            ],
            options={
                'abstract': False,
            },
        ),
        migrations.CreateModel(
            name='Teaching',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('yearStart', models.CharField(max_length=4)),
                ('yearEnd', models.CharField(blank=True, max_length=4)),
                ('name', models.CharField(max_length=50)),
                ('description', models.TextField()),
            ],
            options={
                'abstract': False,
            },
        ),
    ]
