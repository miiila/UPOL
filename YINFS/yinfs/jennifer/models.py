from django.db import models
from slugify import slugify
from ckeditor.fields import RichTextField
from datetime import date


class JennModel(models.Model):
    def __str__(self):
        return self.name

    class Meta:
        abstract = True


class Project(JennModel):
    name = models.CharField(max_length=200)
    description = models.CharField(max_length=500)
    pubDate = models.DateTimeField()


class PublicationType(JennModel):
    name = models.CharField(max_length=100)


class Publication(JennModel):
    name = models.CharField(max_length=200)
    abstract = models.CharField(max_length=500)
    type = models.ForeignKey(PublicationType, on_delete=models.PROTECT)
    authors = models.CharField(max_length=200)
    pubDate = models.DateField()
    isbn = models.CharField(max_length=20, blank=True)


class Section(JennModel):
    name = models.CharField(max_length=30)
    title = models.CharField(max_length=200)
    content = RichTextField()

    def getLink(self):
        return "#"+slugify(self.name)


class Education(JennModel):
    title = models.CharField(max_length=5)
    graduationYear = models.SmallIntegerField()
    name = models.CharField(max_length=50)
    university = models.CharField(max_length=100)


class Position(JennModel):
    yearStart = models.SmallIntegerField()
    yearEnd = models.SmallIntegerField( blank=True)
    name = models.CharField(max_length=50)
    university = models.CharField(max_length=50)
    faculty = models.CharField(max_length=50)


class Award(JennModel):
    yearStart = models.SmallIntegerField()
    title = models.CharField(max_length=15, blank=True)
    yearEnd = models.SmallIntegerField(blank=True)
    name = models.CharField(max_length=50)
    description = models.TextField()


class Teaching(JennModel):
    yearStart = models.SmallIntegerField()
    yearEnd = models.SmallIntegerField(blank=True)
    name = models.CharField(max_length=50)
    description = models.TextField()

    def isActive(self):
        end = self.yearEnd or date.today().year
        return not(int(date.today().year) > end)
