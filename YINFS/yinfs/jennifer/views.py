from django.shortcuts import render

# Create your views here.

from .models import Section


def index(request):
    context = {
        'title': 'Jennifer Doe - homepage',
        'menuItems': Section.objects.all(),
    }

    return render(request, 'index.html', context)